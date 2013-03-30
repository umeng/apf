# Creating an APF Plugin
To create a effective APD plugin, you will need to design plugin interface first in an Android Library Project. Next, implement the declared interface in an Android Application Project. 

## Plugin Interface
* Create an Android Library Project. 

```bash
android create lib-project -n com_example_plugin1_ifs -t android-17 -k com.example.plugin1.ifs -p com.example.plugin1.ifs
```

* Update ant.properties according to the template file `apf-plugin-build/ant.properties.example`
```bash
/apf-opensource/com.example.plugin1$ cat ../apf-plugin-build/ant.properties.example >> ant.properties 
```
The `ant.properties` looks like:

```bash
# This file is used to override default values used by the Ant build system.
#
# This file must be checked into Version Control Systems, as it is
# integral to the build system of your project.

# This file is only used by the Ant script.

# You can use this to override default values such as
#  'source.dir' for the location of your java source folder and
#  'out.dir' for the location of your output folder.

# You can also use it define how the release builds are signed by declaring
# the following properties:
#  'key.store' for the location of your keystore and
#  'key.alias' for the name of the key to use.
# The password will be asked during the build when you use the 'release' target.
key.store=../apf-plugin-build/debug.keystore
key.store.password=android
key.alias=androiddebugkey
key.alias.password=android
```


* Update `build.xml` according to the template file `build.xml.plugin.ifs.example`
```bash
apf-opensource/com.example.plugin1.ifs$ cp ../apf-plugin-build/build.xml.plugin.ifs.example build.xml 
```

* Design the interface to be used by the host application. Check: `src/com/example/plugin1/ifs/`

## Plugin Implementation
* Create an Android Application Project. 

```bash
android create project -n com_example_plugin1 -t android-17 -k com.example.plugin1 -p com.example.plugin1 -a PluginActivity
cd com.example.plugin1
android update project -p ./ --library ../com.example.plugin1.ifs

```

* Update ant.properties according to the template file `ant.properties.example`

* Update `build.xml` according to the template file `build.xml.plugin.ifs.example`
```bash
apf-opensource/com.example.plugin1$ cp ../apf-plugin-build/build.xml.plugin.example build.xml 
```

* Design the interface to be used by the host application.
  `src/com/example/plugin1/ifs/`




> `Plugin Implementation` project will be will packaged/exported as `${package-name}.apk` and `Plugin Interface` project will be packaged to `${package-name}.ifs.jar`.

For example, `com.umeng.analytics.apk` for plugin, and `com.umeng.analytics.ifs.jar` for interfaces declarations.
 
### Build
```bash
> ant deploy
```

The output will be at `${project.dir}/bin/com.example.plugina.apk` and `${project.dir}/bin/com.example.plugina.ifs.jar`.
                                                             
> Note: if the plugin implementation projec ifself is a android library project, for example, like com.umeng.common, you need a little "hack" to the ant build system. First change ant.properties, change `android.library=true` to `android.library=false`. After successfully `ant deploy`, change it back to `android.library=true`, as is required by the upper stream application project. 

### Example
See [com.example.host] and [com.example.plugin1]. 

To Run the example: 
```
ant 






## Signing plugin
All plugins must be signed with private key. APF adopts similar mechanisms as Android app signing. See [Signing Your Applications](http://developer.android.com/tools/publishing/app-signing.html) for help.

### Sign the plugin apk

1. Make sure that you can build your plugin interface and implementation with `ant` successfully before preceeding.
2. Configure `ant.properties`. Add the content of `ant.properties.example` to your plugin implementation project's `ant.properties`.
3. Use `ant deploy` to build the release apk package.
This will build a *release* mode plugin apk called *[project-name]-release.apk* under bin/ directory. 


### Warning
> When to release your plugin, remember to update the key and keystore. Do not use the example key store used here. 
> Caution: Since APF is in very pre-mature state, it's architecture and server side design is subject to change very frequently. For now, APF provided by [Umeng](http://www.umeng.com) does not allow any plugin signed by third party. If you want to try this out and want to deploy your plugin on [Umeng](http://www.umeng.com)'s platform, please develop your plugin first and contact us. After we check out that your plugin is secure, we can sign the plugin for you and host your plugin on  [Umeng](http://www.umeng.com)'s server. Please email to xuxianming at umeng.com. 
We will open source implementation of APF when it's architecture design is freezed. 




