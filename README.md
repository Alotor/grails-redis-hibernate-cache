Grails Redis Hibernate 2nd-level Cache
=====================================

This plugin ease the integration between Grails Hibernate 2nd Level cache and Redis.

## Installation

To install just add the plugin into your BuildConfig.groovy

```groovy
compile ":redis-hibernate-cache:1.0"
```

Then configure your DataSource.groovy to use this cache:

```groovy
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'org.hibernate.cache.redis.GrailsRedisRegionFactory'
    cache.region_prefix = "hibernate"
}
```

## Configuration

You can configure in your Config.groovy the following properties.

```groovy
redis {
   // Connection details (default: localhost:6379)
   host = localhost
   port = 6379

   // Connection timeout
   timeout = 2000

   // Password to access Redis (optional)
   password = secret

   // database for hibernate cache (default 0)
   database=1

   expiryInSeconds {
       // hiberante 2nd cache default expiry (seconds)
       default = 120

       // expiry of hibernate.common region (seconds) // hibernate is prefix, region name is test.Author
       expiryInSeconds.hibernate.test.Author = 0

       // expiry of hibernate.account region (seconds) // hibernate is prefix, region name is test.Book
       expiryInSeconds.hibernate.test.Book = 1200
   }
}
```

## Acknowledges
This plugin uses under-the-hood the great hibernate-redis library by debop:

https://github.com/debop/hibernate-redis

And the Java Redis library Jedis by xetorthio:

https://github.com/xetorthio/jedis

## Versions
- 2014/11/XX. v1.0 - First version
