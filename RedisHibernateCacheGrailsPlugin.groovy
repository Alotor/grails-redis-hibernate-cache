class RedisHibernateCacheGrailsPlugin {
    def version = "1.0"
    def grailsVersion = "2.0 > *"
    def dependsOn = [ hibernate4 : "* > 4.3.5" ]

    def pluginExcludes = [
        "grails-app/views/error.gsp",
        "grails-app/controllers/**",
        "grails-app/domain/**",
        "grails-app/i18n/**",
        "grails-app/services/**",
        "grails-app/taglib/**",
        "grails-app/utils/**",
        "grails-app/views/**",
        "web-app/**"
    ]

    def title = "Redis Hibernate Cache Plugin" // Headline display name of the plugin
    def author = "Alonso Torres"
    def authorEmail = "alonso.javier.torres@gmail.com"
    def description = "Grails integration with Redis as a backend for Hibernate 2nd level cache"
    def documentation = "http://grails.org/plugin/redis-hibernate-cache"
    def license = "APACHE"
    def organization = [ name: "Kaleidos", url: "http://www.kaleidos.net" ]
    def issueManagement = [ system: "GITHUB", url: "https://github.com/alotor/grails-redis-hibernate-cache/issues" ]
    def scm = [ url: "https://github.com/alotor/grails-redis-hibernate-cache" ]
}
