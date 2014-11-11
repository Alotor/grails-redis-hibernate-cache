grails.project.work.dir = "target/work"

grails.project.fork = [
    test: false,
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven"

grails.project.dependency.resolution = {
    log "warn"

    inherits("global")

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
        mavenRepo "http://dl.bintray.com/debop/maven"
    }

    dependencies {
        compile "com.github.debop:hibernate-redis:1.6.1", {
            excludes "hibernate-core", "hibernate-entitymanager", "logback-classic"
        }
    }

    plugins {
        /*
        build(":release:3.0.1",
              ":rest-client-builder:1.0.3") {
            export = false
        }
        */
        compile ":hibernate4:4.3.6.1", {
            export = false
        }
    }
}
