/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */
class JmxGriffonPlugin {
    // the plugin version
    String version = '0.6'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '0.9.5 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [spring: '0.9']
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = []
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-jmx-plugin'

    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'Enables JMX on a Griffon applicatin'
    // accepts Markdown syntax. See http://daringfireball.net/projects/markdown/ for details
    String description = '''
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
'''
}
