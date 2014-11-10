/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibernate.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.redis.util.JedisTool;
import org.hibernate.cache.redis.jedis.JedisClient;
import org.hibernate.cfg.Settings;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import grails.util.Holders;

/**
 * Grails-properties region factory based on the Singleton Implementation
 */
@Slf4j
public class GrailsRedisRegionFactory extends AbstractRedisRegionFactory {
    private static final long serialVersionUID = 1L;
    private static final AtomicInteger ReferenceCount = new AtomicInteger();

    public GrailsRedisRegionFactory(Properties props) {
        super(props);
        log.info("create GrailsRedisRegionFactory instance.");
    }

    @Override
    public synchronized void start(Settings settings, Properties properties) throws CacheException {
        log.info("starting GrailsRedisRegionFactory...");

        this.settings = settings;
        try {
            if (redis == null) {
                Properties grailsProperties = Holders.getConfig().toProperties();
                log.debug("Loaded config: {}", grailsProperties);
                Integer expiryInSeconds =
                    Integer.decode(grailsProperties.getProperty("redis.expiryInSeconds.default", "120"));

                this.redis = new JedisClient(JedisTool.createJedisPool(grailsProperties), expiryInSeconds);
                manageExpiration(this.redis);
            }
            ReferenceCount.incrementAndGet();
            log.info("Started GrailsRedisRegionFactory");
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public synchronized void stop() {
        log.debug("stopping GrailsRedisRegionFactory...");

        if (ReferenceCount.decrementAndGet() == 0) {
            try {
                redis = null;
                if (expirationThread != null) {
                    expirationThread.interrupt();
                }
                log.info("stopped GrailsRedisRegionFactory");
            } catch (Exception ignored) {
                log.debug("Exception: {}", ignored);
            }
        }
    }
}
