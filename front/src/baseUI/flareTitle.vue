<template>
    <span class="shiny h100">
        <span class="inner-shiny"></span>
    </span>
</template>

<script>
    export default {
        name: "flareTitle",
        data() {
            return {}
        },
        mounted() {

        },
        props: {

        },
        methods: {

        }
    }
</script>

<style lang="scss" scoped>

    .shiny {
        color: #93c4f1;
        background: -webkit-gradient(linear, left top, left bottom, from(#00daff), to(#0f3c91));
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        display: inline-block;
        font-size: 33px;
        font-weight: 500;
        position: relative;
        text-transform: uppercase;
    }

    .shiny::before {
        animation: flare 5s infinite;
        animation-timing-function: linear;
        background-image: linear-gradient(65deg, transparent 20%, rgba(255, 255, 255, 0.2) 20%, rgba(255, 255, 255, 0.3) 27%, transparent 27%, transparent 100%);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        content: "共享航次计划数据成果发布";
        color: #fff;
        display: block;
    }


    .inner-shiny::after,
    .inner-shiny::before {
        animation: sparkle 5s infinite;
        animation-timing-function: linear;
        background: #fff;
        border-radius: 100%;
        box-shadow: 0 0 5px #fff, 0 0 10px #fff, 0 0 15px #fff, 0 0 20px #fff, 0 0 25px #fff, 0 0 30px #fff, 0 0 35px #fff;
        content: "共享航次计划数据成果发布";
        display: block;
        height: 10px;
        opacity: 0.7;
        position: absolute;
        width: 10px;
    }

    .inner-shiny::before {
        -webkit-animation-delay: 0.2s;
        height: 7px;
        left: 0.12em;
        top: 0.8em;
        width: 7px;
    }

    .inner-shiny::after {
        top: 0.32em;
        right: -5px;
    }

    @-webkit-keyframes flare {
        0% {
            background-position: 0;
        }

        30% {
            background-position: 200px;
        }

        100% {
            background-position: 400px;
        }
    }

    @-webkit-keyframes sparkle {
        0% {
            opacity: 0;
        }

        30% {
            opacity: 0;
        }

        40% {
            opacity: 0.8;
        }

        60% {
            opacity: 0;
        }

        100% {
            opacity: 0;
        }
    }
</style>