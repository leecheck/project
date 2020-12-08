<template>
    <div class="panel p100">
        <div style="overflow-y:auto" class="relative p100 padding3 border-box">
            <slot></slot>
        </div>
        <div class="panel-footer"></div>
    </div>
</template>

<script>
    export default {
        name: "SqurePanel",
        components: {},
        props: {},
        data() {
            return {

            }
        },
        computed: {},
        watch: {},
        mounted() {

        },
        methods: {
            close() {
                this.$emit('close')
            }
        }
    }
</script>

<style lang="scss" scoped>

    .panel {
        box-sizing: border-box;
        position: relative;
        border-top: 2px solid #69e0fbee;
        border-bottom: 2px solid #69e0fbee;
        background: $themeColorTp;

        &::before {
            position: absolute;
            top: 0px;
            left: 0px;
            width: 18px;
            height: 10px;
            border-top: 2px solid #69e0fb;
            content: "";
        }

        &::after {
            position: absolute;
            top: 0px;
            right: 0px;
            width: 18px;
            height: 10px;
            border-top: 2px solid #69e0fb;
            content: "";
        }

        .panel-footer {
            position: absolute;
            bottom: 0px;
            left: 0px;
            width: 100%;

            &::before {
                position: absolute;
                bottom: 0px;
                right: 0px;
                width: 18px;
                height: 10px;
                border-bottom: 2px solid #69e0fb;
                content: "";
            }

            &::after {
                position: absolute;
                bottom: 0px;
                left: 0px;
                width: 18px;
                height: 10px;
                border-bottom: 2px solid #69e0fb;
                content: "";
            }
        }
    }
</style>