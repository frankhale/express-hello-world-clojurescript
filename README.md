# express-hello-world-clojurescript
Express Hello, World written in Clojurescript

This is the first iteration so things are bound to be ugly and able to be made
more clean.

NOTE:

Once compiled routes.js and hello-express.js are dumped in the root of source
folder. This will be fixed in the future.

Additionally, I've used npm explicitly for the node modules. This will be
changed to use lein-npm so that you won't have to run `npm install` building via
lein.

One last thing is that simple compilation has been used and it'd be really nice
to have a dev / prod build configuration and prod having advanced compilation.
This will likely require an externs file to define all the symbols that cannot
be munged during the advanced compile process.

## Author(s)

Frank Hale &lt;frankhale@gmail.com&gt;  
28 September 2015

## License

GNU GPL v2 - see [LICENSE](LICENSE)
