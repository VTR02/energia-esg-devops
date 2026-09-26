package br.com.fiap.energia_ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {

        return """
        <html>
        <head>
            <title>API ESG - Energia Inteligente</title>

            <style>

                body{
                    font-family: Arial;
                    background-color:#f4f4f4;
                    text-align:center;
                    padding:50px;
                }

                h1{
                    color:#2E8B57;
                }

                .container{
                    width:500px;
                    margin:auto;
                    background:white;
                    padding:30px;
                    border-radius:10px;
                    box-shadow:0 0 10px gray;
                }

                a{
                    display:block;
                    margin:15px;
                    padding:10px;
                    background:#2E8B57;
                    color:white;
                    text-decoration:none;
                    border-radius:5px;
                }

                a:hover{
                    background:#1d5f3a;
                }

            </style>

        </head>

        <body>

            <div class="container">

                <h1>API ESG - Eficiência Energética</h1>

                <p>Sistema de monitoramento de energia</p>

                <a href='/equipamentos'>Equipamentos</a>

                <a href='/consumo'>Consumo Energia</a>

                <a href='/limites'>Limites de Consumo</a>

                <a href='/alertas'>Alertas</a>

                <a href='/desligamentos'>Desligamentos</a>

            </div>

        </body>

        </html>
        """;
    }

}