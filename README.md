
Enables JMX on a Griffon applicatin
-----------------------------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/jmx](http://artifacts.griffon-framework.org/plugin/jmx)


This plugin adds JMX MBeanServer support through spring and configures a number MBeans.
A direct port of the [JMX plugin][1] for [Grails][2]. Original plugin made by Ken Sipe.

Plugin features:

 * Configures an MBeanServer
 * Exposes any Service configured for jmx
 * Allows other Spring beans to be configured for jmx
 * Addons may expose additional beans

Usage
-----

There is nothing to do after the install if you have no services to expose. Just run the application and use jconsole to administrate.

### Exposing Services

By default, the plugin only supports exposing Services. This is accomplished by add 'jmx' to the static expose property as outlined 
below:

        class StateService {
            static expose = ['jmx']
            ...
        }

This will expose the service leverage a convention for the service name. Alternatively you can be very explicit on the object
through the following example:

        class CountryService {
            static expose = ['jmx:service=Country,type=special']
            ...
        }

### Exposing Spring beans

The plugin also supports exposing other Spring Beans - e.g. registered via `src/spring/resources.groovy`. This is accomplished 
by adding _jmx_ to the static expose property as outlined above and using the following code in `Config.groovy`

        griffon {
            jmx {
                exportBeans = ['myBean']
            }
        }
        
### Exposing beans on addons

Any addon may expose additional beans to the MBeanServer by adding a exposeWithJmx closure property to its descriptor.
The following example shows how the [Datasource Plugin] exposes its database

        // exporter is of type MBeanExporter
        // domain is of type String
        // ctx is of type ApplicationContext
        def exportwithJmx = { exporter, domain, ctx ->
            String beanKey = "${domain}:service=datasource,type=configuration"
            exporter.beans[beanKey] = DataSourceHolder.instance.getDataSource('default')
        }

[1]: http://grails.org/plugin/jmx
[2]: http://grails.org/
[3]: /plugin/datasource

