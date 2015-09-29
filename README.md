# express-hello-world-clojurescript
Express Hello, World written in Clojurescript

This is an evolving experiment so things are bound to be ugly non-idiomatic.

**NOTE:**

Once compiled hello-express.js are dumped in the root of source folder. This is okay for now but would be nice if it was created in a build folder and was able to reference the views / resources folder accordingly. I'll fix this later.

I've changed how the node modules are installed and tracked. I'm using lein-npm now to manage that. You may need to run 'lein deps' in order to install these if you plan on running this code.

One last thing is that simple compilation has been used and it'd be really nice to have a dev / prod build configuration and prod having advanced compilation. This will likely require an externs file to define all the symbols that cannot be munged during the advanced compile process.

Just one more thing, I'm using the handlebars templating engine for now but it'd be ideal to use something like hiccups but I don't know if that is compatible with Express.

Hiccups - https://github.com/teropa/hiccups

## Author(s)

Frank Hale &lt;frankhale@gmail.com&gt;  
29 September 2015

## License

GNU GPL v2 - see [LICENSE](LICENSE)
