# qrmeStack
backend - запуск в intellij, должна быть создана база в postgreSQL qrme_db, в properties файлах можно задать свои настройки
Сервер крутится на localhost:8081
Прим, post запроса: http://localhost:8081/api/cards/
json:
{
    "id": 8,
    "type": "SIMPLE",
    "name": "John Doe"
}
Get запрос: http://localhost:8081/api/cards/8
Вернет json:
{
    "id": 8,
    "type": "SIMPLE",
    "name": "John Doe"
}

frontend - запуск через yarn

mobile - скомпилить файл
