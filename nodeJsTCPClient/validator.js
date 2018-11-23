function isNumeric(string){return !isNaN(string)
}
function validateString(message){return true}

function validateInteger(message){return isNumeric(message)}

function validateChar(message){return message.length == 1}

module.exports =
{
    validate:function(type,message){
        switch(type){
            case 1:
                return validateString(message);
            case 2:
                return validateInteger(message);
            case 3:
                return validateChar(message);
        }
    }
}