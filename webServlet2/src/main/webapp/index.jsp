<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Калькулятор</title>
</head>
<body>
    <h1>Калькулятор</h1>
    <form id="calculatorForm">
        <label for="number1">Число а:</label>
        <input type="number" id="number1" name="a" required>
        <br>
        <label for="number2">Число b:</label>
        <input type="number" id="number2" name="b" required>
        <br>
        <label for="operation">Арифметическое действие:</label>
        <select id="operation" name="math" required>
            <option value="+">Сложение</option>
            <option value="-">Вычитание</option>
            <option value="*">Умножение</option>
            <option value="/">Деление</option>
        </select>
        <br>
        <button type="button" onclick="calculate()">Вычислить</button>
    </form>
    <div id="result"></div>

    <script>
        function calculate() {
            var a = document.getElementById("number1").value;
            var b = document.getElementById("number2").value;
            var math = document.getElementById("operation").value;

            fetch("/calculator", {
                method: "POST",
                body: JSON.stringify({ a: a, b: b, math: math }),
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(response => response.json())
            .then(data => {
                document.getElementById("result").innerText = "Результат: " + data.result;
            })
            .catch(error => {
                console.error("Ошибка при запросе к серверу:", error);
                document.getElementById("result").innerText = "Произошла ошибка при выполнении запроса.";
            });
        }
    </script>
</body>
</html>
