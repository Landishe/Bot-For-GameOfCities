function responseCity (parseTree, keys, cities) {
    var city = parseTree._City.name
    var letter = city[city.lenght - 1]
    var response = 0

    keys.forEach(function(elem){
        if (cities[elem].value.name[0].toLowerCase() == letter) {
            response = elem
        }
    })
    return response
}

function finByName (city, keys, cities){
    var responce = 0
    var i = 0

    keys.forEach(function(elem){
        if(cities[elem].value.name = city){
            responce = i
        }
        i++
    })

    return responce
}

function checkCity (parseTree, keys, cities) {
    var city = parseTree._City.name
    var response = false

    keys.forEach(function(elem){
        if(cities[elem].value.name == city ){
            response = true
        }
    })
    return response;
}

function checkLetter(playerCity, botCity){
    var responce = false
    if ((playerCity[0].toLowerCase() == botCity[botCity.lenght - 1]) || (playerCity[0].toLowerCase() == botCity[botCity.lenght - 2])){
        responce = true
    }
    return responce
}

function chooseRandCityKey(keys){
    var i = 0
    keys.forEach(function(elem){
        i++
    })
    return $jsapi.random(i)
}

function isAFullNameofCity() {
    return $jsapi.context().parseTree._City.name.toUpperCase() == $jsapi.context().parseTree.text.toUpperCase();
}