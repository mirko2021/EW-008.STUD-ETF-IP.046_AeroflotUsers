<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Добавление метки с собственным изображением</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--
        Укажите свой API-ключ. Тестовый ключ НЕ БУДЕТ работать на других сайтах.
        Получить ключ можно в Кабинете разработчика: https://developer.tech.yandex.ru/keys/
    -->
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=" type="text/javascript"></script>
    <script src="icon_customImage.js" type="text/javascript"></script>
</head>
<body>
<input type='button' value='cli' id='cli' onclick='go()'/>
<div id="mapa" style='width:300px; height:300px'></div>
</body>
</html>
    