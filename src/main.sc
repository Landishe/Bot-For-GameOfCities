require: responseCity.js

require: city/city.sc
    module = sys.zb-common

theme: /
    
    state: Start || modal = true
        q!: $regex</start>
        intent!: /LetsPlay
        script: 
            $session = {}
            $client = {}
            $temp = {}
            $response = {}

        a: Привет! Предлагаю сыграть в игру "Города". Кто загадывает город: компьютер или пользователь?

        state: User
            intent: /User
            a: Назовите город
            script:
                $session.keys = Object.keys($Cities);
                $session.prevBotCity = 0;
            go!: /LetsPlayCitiesGame

        state: Computer
            intent: /Computer
            script: 
                $session.keys = Object.keys($Cities)
                var city = $Cities[chooseRandCityKeys($session.keys)].value.name
                $reactions.answer(city)
                $session.prevBotCity = city

            go!: /LetsPlayCitiesGame

        state: /LocalCatchAll
            event: NoMatch
            a:Это не похоже на ответ. Попробуйте еще раз.
        
    state: /LetsPlayCitiesGame
        state: CityPattern
            q: * $City *
            script: 
                if(isAllFullNameOfCity){
                    if(checkLetter($parseTree._City.name, $session.prevBotCity) == true || $session.prevBotCity == 0){
                        var removeCity = findByName($parseTree._City.name, $session.keys, $Cities)

                        if(checkLetter($parseTree, $session.keys, $Cities) == true){
                            $session.keys.splice(removeCity, 1)
                            var keys = responseCity($parseTree, $session.keys, $Cities)
                            if(key == 0) {
                                $reactions.answer("Я сдаюсь")
                            } else {
                                $reactions.answer($Cities[key].value.name)
                                $session.prevBotCity = $Cities[key].value.name
                                removeCity = findByName($Cities[key].value.name, $session.keys, $Cities)
                                $session.keys.splice(removeCity, 1)
                            }
                        } else $reactions.answer("Этот город уже был назван")
                        }
                } else $reactions.answer("Используйте только полные названия городов")

        state: NoMatch
            event!: noManch
            a: Я не знаю такого города. Попробуйте ввести другой город
        
    state: EndGame
        intent!: /endThisGeme
        a: Очень жаль! Если передумаешь — скажи "давай поиграем"
