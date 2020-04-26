var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var port = 8080;

server.listen(port, function(){
    console.log("Server is running in port " + port + "!")
});

io.on("connection", function(socket){
    socket.emit("socketID", { id : socket.id});
    

});