const net = require('net');
const validator = require('./validator');
const inquirer = require('inquirer');

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


function doQuestion(){
    inquirer.prompt(questions).then(answers => {
        type = answers.type;
        body = answers.body;
        isValid = validator.validate(type,body);
        if(isValid)
            client.write(getMessage(type,body));
        else{
            console.log("Esse tipo não permite essa menssagem")
            doQuestion();
        }
    });
}

var client = new net.Socket();
client.connect(PORT, HOST, function() {
    console.log('CONNECTED TO: ' + HOST + ':' + PORT);
    doQuestion();
});

client.on('data', function(data) {
    console.log("Received:", data.toString())
    client.destroy();
});

client.on('close', function() {
    console.log('Connection closed');
});