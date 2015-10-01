# express-hello-world-clojurescript
Express Hello, World written in Clojurescript

This is an evolving experiment so things are bound to be ugly non-idiomatic.

**NOTE:**

Once compiled hello-express.js are dumped in the root of source folder. This is
okay for now but would be nice if it was created in a build folder and was able
to reference the views / resources folder accordingly. I'll fix this later.

I've changed how the node modules are installed and tracked. I'm using lein-npm
now to manage that. You may need to run 'lein deps' in order to install these
if you plan on running this code.

I've removed the the handlebars view engine and views and replaced it with
hiccups. We have to use a custom compiled local copy because it contains
features we need that have not been released yet. This choice was done because
the maintainer of hiccups also announced that he is looking for a new maintainer
so that likely means he is not likely to release the new features that were
recently merged.

Hiccups - https://github.com/teropa/hiccups

**PROD BUILD**

If you build the production build you'll get the following warning coming from
the supplied externs (see project.clj). We need externs because advanced
compilation is turned on. I've elected to tell the externs section of
project.clj to use the actual node module javascript files instead of writing
out the externs by hand. Keep in mind that not all of the node module
dependencies have been added to the externs section.

Even with externs validation turned off this still shows up. It doesn't seem to
cause any problems.

```
WARNING - Misplaced function annotation. This JSDoc is not attached to a function node. Are you missing parentheses?
```

It looks like Clojurescript master can now support ecmascript 6 language-in and
out features and I found a reference to this specific error and that we need to
use that new feature. It hasn't been released yet and since this is just a
warning I am not in a big hurry to use a custom compiled local copy of
Clojurescript just to make the warnings go away.

##Additional Notes

A custom build of hiccups is being used and is pulled from the repo directory.

If other custom dependencies are needed that are not in a maven repo online you
can install them using the following command:

Assumed maven is install and in path. Replace the name, version and file name accordingly.

```
mvn deploy:deploy-file -DgroupId=local -DartifactId=hiccups -Dversion=0.4.0-SNAPSHOT -Dpackaging=jar -Dfile=hiccups-0.4.0-SNAPSHOT.jar -Durl=file:repo
```

## Author(s)

Frank Hale &lt;frankhale@gmail.com&gt;  
30 September 2015

## License

GNU GPL v2 - see [LICENSE](LICENSE)
