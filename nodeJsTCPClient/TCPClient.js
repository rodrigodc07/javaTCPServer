const net = require('net');

const HOST = '127.0.0.1';
const PORT = 12345;

const HEAD_SEPARATOR = "\n"
const MESSAGE_EOF = "\uFFFF"

function getMessage(type,body) {  
  return type + HEAD_SEPARATOR + body + MESSAGE_EOF;
}

function handleRawType(type) {  
  switch (type.toLowerCase()) {
    case "string":
    	return 1; 
    case "int":
    	return 2;   
    case "char":
    	return 3;
    default:
        return -1;
	}
}

var inquirer = require('inquirer');

var questions = [
  {
    type: 'list',
    name: 'type',
    message: 'Qual o tipo da sua Menssagem (String | Integer | Char)',
    choices: ['String', 'Char', 'Int'],
    filter: function(val) {
      return handleRawType(val);
    }
  },
  {
    type: 'input',
    name: 'body',
    message: "Digite A Menssagem "
	}
];

function validateString(message){return true}

function validateInteger(message){return message.length == 1}

function validateChar(message){return message.length == 1}

function validate(type,message){
    switch(type){
        case 1:
            return validateString(message);
        case 2:
            return validateInteger(message);
        case 3:
            return validateChar(message);
    }
}

function doQuestion(){
    inquirer.prompt(questions).then(answers => {
        type = answers.type;
        body = answers.body;
        isValid = validate(type,body);
        if(isValid)
            client.write(getMessage(type,body));
        else{
            console.log("Menssagem não pode ser desde tipo")
            doQuestion();
        }
    });
}

var client = new net.Socket();
client.connect(PORT, HOST, function() {
    console.log('CONNECTED TO: ' + HOST + ':' + PORT);
    doQuestion();
});

// Add a 'data' event handler for the client socket
// data is what the server sent to this socket
client.on('data', function(data) {
    console.log("Received:", data.toString())
    client.destroy();
});

// Add a 'close' event handler for the client socket
client.on('close', function() {
    console.log('Connection closed');
});