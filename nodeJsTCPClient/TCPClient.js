const net = require('net');
const validator = require('./validator');

const HOST = process.env.HOST;
const PORT = 9010;

const HEAD_SEPARATOR = "\n"
const MESSAGE_EOF = "\uFFFF"

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

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

var startTime;

function doQuestion(){
	rl.question('Qual o tipo da sua Menssagem (String | Int | Char)\n', (type) => {
		type = handleRawType(type);
		rl.question('Digite A Menssagem ', (body) => {
		    isValid = validator.validate(type,body);
     		rl.close();
            if(isValid){
                startTime = Date.now()
                client.write(getMessage(type,body));
            }
            else{
                console.log("Esse tipo não permite essa menssagem")
                doQuestion();
            }
   		});
	});
}

var client = new net.Socket();
client.connect(PORT, HOST, function() {
    console.log('Conectado com: ' + HOST + ':' + PORT);
    doQuestion();
});

client.on('data', function(data) {
    console.log("Resposta:", data.toString())
    client.destroy();
});

client.on('close', function() {
    rtt = Date.now() - startTime;
    console.log('Encerrando Conexão apos '+ rtt +' ms');
});