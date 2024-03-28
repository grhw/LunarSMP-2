package light.breeze.website.webbukit;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class BasicWebsiteRequestHandler implements WebsiteRequestHandler {
    @Override
    public String handle( HttpExchange exchange, String request ) throws IOException {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>LunarSMP</title>
                    <style>
                        :root {
                    --bg-1: #180837;
                    --bg-2: #2a0c56;
                                
                    --col-1: #370e70;
                    --col-2: #47128c;
                    --col-3: #661eb4;
                    --col-4: #FF96FF;
                }
                    </style>
                    <style>
                        @import url('https://fonts.googleapis.com/css2?family=Kode+Mono:wght@400..700&display=swap');
                html,body {
                    background-color: black;
                    overflow: hidden;
                    margin: 0;
                }
                * {
                    font-family: "Kode Mono", monospace;
                    font-optical-sizing: auto;
                    font-style: normal;
                    color: var(--col-1);
                    text-align: center;
                    font-size: 1.25rem;
                }
                .lunarsmp {
                    color: var(--col-4)
                }
                                
                *.bg {
                    background: hsla(259, 42%, 10%, 1);
                                
                    background: linear-gradient(0deg, hsla(259, 42%, 10%, 1) 0%, hsla(258, 42%, 13%, 1) 37%, hsla(261, 42%, 15%, 1) 51%, hsla(258, 42%, 13%, 1) 63%, hsla(259, 42%, 10%, 1) 100%);
                    width: 100vw;
                    height: 100vh;
                    position: sticky;
                }
                                
                .effects {
                    position: absolute;
                    top: 0;
                    bottom: 0;
                    width: 100vw;
                    height: 100vh;
                    pointer-events: none;
                }
                                
                .effects * {
                    top: 0;
                    bottom: 0;
                    left: 0;
                    right: 0;
                    width: 100%;
                    height: 100%;
                    position: absolute;
                }
                                
                .idkwhatthisiscalled {
                    background: hsla(266, 43%, 40%, 1);
                    background: linear-gradient(90deg, hsla(266, 43%, 40%, 1) 0%, hsla(258, 43%, 42%, 1) 17%, hsla(270, 40%, 47%, 1) 44%, hsla(269, 38%, 51%, 1) 70%, hsla(266, 43%, 40%, 1) 100%);
                    mask-image: url("https://lh7-us.googleusercontent.com/pFnV87GK97wS_iPZ9NKNTQtH7PEMTGKboZ_povxA4ILwUT2L5LflUUkF3j0PX0W-P3_qEnxSGE_vAkoqVogpYpPhXQbZ9XXm0jhAy-DJW8N0r_zCHwc2gFTzUIbiEKhjVxHse68AKc4EwOvK_0PXt74QPrnojQ");
                    mask-repeat: no-repeat;
                    mask-size: 100% 100%;
                    opacity: 0.25;
                }
                                
                .content {
                    position: absolute;
                    top: 0;
                    bottom: 0;
                    width: 100%;
                    height: 100%;
                }
                                
                .banner {
                    background-image: url("https://lh7-us.googleusercontent.com/kBJWuLkM_tgcViJFNWHZwczNhy00HVkpUG27HEgE83hsmpycxHZ2mw7Eo4RjczK-JQXT6DcbHoRFtXOgHpBD8yRVR28Qdk-Z1Pmct2_O8PEazHMM3IrKqeVsTtrUssyCi8upjXoBZir8hxZ0lKfzcnyNA9Cnrg");
                    background-repeat: no-repeat;
                    background-size:cover;
                    background-position: top;
                    width: 90%;
                    height: 90%;
                    margin: auto;
                    position: relative;
                    top: 5%;
                    border-radius: 10px;
                }
                                
                @keyframes blink {
                	0% {
                		opacity: 1;
                	}
                                
                	50% {
                		opacity: 0;
                	}
                                
                    100% {
                		opacity: 1;
                	}
                }
                                
                .banner-over {
                    background-image: url("https://lh7-us.googleusercontent.com/q9XjO7veME2os3tjlRUCUWNs6h0r3ihLjoV3s01FVBCTSVyrKp6dhYPg0vcQl69LcXjTEIh6ya4HYC5X0BTbVQ0lqrSLF7udzp4jc7VrBup8pG0A-O0jviLur5OA7klQusaV9MLPrRees8F-khCZ6K_WCdyliw");
                    animation: blink 10s cubic-bezier(0.37, 0, 0.63, 1) 0s 1 normal forwards;
                    animation-iteration-count: infinite;
                    height: 100%;
                    width: 100%;
                    top: 0%;
                }
                                
                .panel-wrapper {
                    top: 35%;
                    position: relative;
                }
                                
                .panel  {
                    position: absolute;
                    display: block;
                    background-color: rgba(255, 255, 255, 0.25);
                    backdrop-filter: blur(3px);
                    border: 1px white solid;
                    z-index: 25;
                }
                                
                .main-panel {
                    perspective: 400px;
                    transform-style: preserve-3d;
                    left: 35%;
                    width: 30%;
                    aspect-ratio: 2;
                }
                                
                .top-panel {
                    left: -1px;
                    width: 100%;
                    top: 0;
                    aspect-ratio: 10;
                    transform: scale(0.965) rotateX(-12deg) translateY(-110%);
                    min-width:fit-content;
                }
                                
                .bottom-panel {
                    aspect-ratio: 10;
                    width: 100%;
                    left: -1px;
                    top: 100%;
                    transform: scale(1.02) translateY(20%) rotateX(12deg);
                    min-width:max-content;
                    min-height:max-content;
                }
                                
                .left-panel {
                    width: 45%;
                    height: 90%;
                    transform: rotateY(10deg) translateX(-100%) translateY(5%);
                }
                                
                ::-webkit-scrollbar {
                    width: 0;
                }
                                
                .right-panel {
                    width: 50%;
                    right: 1px;
                    height: 90%;
                    transform: rotateY(-10deg) translateX(100%) translateY(5%);
                    overflow-y: scroll;
                }
                                
                h1,.lunarsmp {
                    font-size: 2rem;
                }
                                
                .discord {
                    display: block;
                    transform: translateY(25%);
                    font-size: 2rem;
                }
                    </style>
                </head>
                <body>
                    <div class="bg">
                        <div class="content">
                            <div class="panel-wrapper">
                                <div class="main-panel panel">
                                    <div class="top-panel panel">
                                        <h1><span class="lunarsmp">lunarsmp.apexmc.co</span>:25594</h1>
                                    </div>
                                    <div class="bottom-panel panel">
                                        <a class="discord" href="https://discord.gg/7hPaCcnA9R">Join our discord server!</a>
                                    </div>
                                    <div class="left-panel panel">
                                        <div class="banner"><div class="banner banner-over"></div></div>
                                    </div>
                                    <div class="right-panel panel">
                                        <h1>Contributors</h1>
                                        <p>Gust - Owner/Dev</p>
                                        <p>MiiToe - Co-Owner</p>
                                        <p>Manolo - Textures</p>
                                        <p>Square_Guy - Structures</p>
                                        <img src="images/credit.png">\s
                                    </div>
                                    <h1>Welcome to <span class="lunarsmp">Lunar SMP</span></h1>
                                    <p>A small SMP ran by some wind (@guhw)</p>
                                    <br>
                   \s
                                    <p>We have custom plugins for this SMP.</p>
                                    <p>^ Includes weapons, potions, and more</p>
                                </div>
                            </div>
                        </div>
                        <div class="effects">
                            <div class="idkwhatthisiscalled"></div>
                            <canvas width="1600" height="900" class="stars"></canvas>
                        </div>
                    </div>
                    <script defer>const canvas = document.querySelector(".stars");
                        const context = canvas.getContext("2d");
                        var stars = []
                       \s
                        for (let i = 0; i < 60; i++) {
                            stars.push([Math.random()*1600,Math.random()*900])
                        }
                       \s
                        setInterval(function() {
                            context.clearRect(0,0,1600,900)
                            stars.forEach(star => {
                                star[1] -= 0.1
                                if (star[1] < -10) {
                                    star[1] = 910
                                    star[0] = Math.random()*1600
                                }
                                context.beginPath();
                                context.fillStyle = "white";
                                context.arc(star[0],star[1],1,0, 2 * Math.PI);
                                context.fill();
                            });
                        },1)</script>
                    <script defer>const content = document.querySelector(".main-panel")
                        document.addEventListener("mousemove", (e)=>{
                            content.style.transform = `rotateY(${(e.clientX/document.body.clientWidth)*10}deg) rotateX(${(e.clientY/document.body.clientHeight)*10}deg)`
                        })</script>
                </body>
                </html>""";
    }
}
