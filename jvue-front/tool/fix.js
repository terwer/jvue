var mv = require('mv');

mv('dist/index.html', 'dist/index.ssr.html', function(err) {
    // done. it tried fs.rename first, and then falls back to
    // piping the source file to the dest file and then unlinking
    // the source file.
    if(err){
        console.log("err");
        return
    }
    console.log("move from dist/index.html to dist/index.ssr.html success!");
    console.log("done.");
});