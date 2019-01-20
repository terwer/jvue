<template>
    <div id="header">
        <div class="container">
            <div class="phone-menu">
                <div class="style-1">
                    <div class="iphone__screen">
                        <a href="javascript:void(0);" class="nav__trigger"><span class="nav__icon"></span></a>
                        <nav class="nav">
                            <div class="menu-nav-container">
                                <ul id="menu-nav" class="nav__list">
                                    <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home">
                                        <a href="/"><i class="fa fa-home"></i><span class="fontawesome-text"> 首页</span></a></li>
                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-category">
                                        <a href="/essay"><i class="fa fa-file-text"></i><span class="fontawesome-text"> 随笔</span></a>
                                    </li>
                                    <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children">
                                        <a href="#"><i class="fa fa-file"></i><span class="fontawesome-text"> 页面</span></a>
                                        <ul class="sub-menu">
                                            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">页面默认模板</a></li>
                                            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">页面模板01</a></li>
                                            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">页面模板02</a></li>
                                            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">标签页面</a></li>
                                            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">用户列表页</a></li>
                                        </ul>
                                    </li>
                                    <li class="menu-item menu-item-type-post_type menu-item-object-page">
                                        <a href="#"><i class="fa fa-book"></i><span class="fontawesome-text"> 专题</span></a></li>
                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-album_category">
                                        <a href="#"><i class="fa fa-picture-o"></i><span class="fontawesome-text"> 图集</span></a>
                                    </li>
                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-download_category">
                                        <a href="#"><i class="fa fa-download"></i><span class="fontawesome-text"> 下载</span></a>
                                    </li>
                                    <li class="menu-item menu-item-type-taxonomy menu-item-object-video_category">
                                        <a href="#"><i class="fa fa-file-video-o"></i><span class="fontawesome-text"> 视频</span></a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <h1 class="logo">
                <a href="/" title="terwer">
                    <img src="./images/logo.png" alt="terwer">
                </a>
            </h1>
            <div class="menu">
                <div class="menu-nav-container">
                    <ul class="next-menu">
                        <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home">
                            <a href="/"><i class="fa fa-home"></i><span class="fontawesome-text"> 首页</span></a>
                        </li>
                        <li class="menu-item menu-item-type-taxonomy menu-item-object-category">
                            <a href="/essay"><i class="fa fa-file-text"></i><span class="fontawesome-text"> 随笔</span></a>
                        </li>
                        <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children ">
                            <a href="#"><i class="fa fa-file"></i><span class="fontawesome-text"> 页面</span></a>
                            <ul class="sub-menu">
                                <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">页面默认模板</a></li>
                                <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">页面模板01</a></li>
                                <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">页面模板02</a></li>
                                <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">标签页面</a></li>
                                <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#">用户列表页</a></li>
                            </ul>
                        </li>
                        <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="#"><i class="fa fa-book"></i><span class="fontawesome-text"> 专题</span></a></li>
                        <li class="menu-item menu-item-type-taxonomy menu-item-object-album_category"><a href="#"><i class="fa fa-picture-o"></i><span class="fontawesome-text"> 图集</span></a></li>
                        <li class="menu-item menu-item-type-taxonomy menu-item-object-download_category"><a href="#"><i class="fa fa-download"></i><span class="fontawesome-text"> 下载</span></a></li>
                        <li class="menu-item menu-item-type-taxonomy menu-item-object-video_category"><a href="#"><i class="fa fa-file-video-o"></i><span class="fontawesome-text"> 视频</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="header-right">
                <div class="loginlink">
                    <a href="javascript:void(0);" @click="showModal"><i class="fa fa-search" aria-hidden="true"></i><span class="smhidden">搜索</span></a>
                    <a class="smhidden" href="/auth/login"><i class="fa fa-user-circle" aria-hidden="true"></i>登录</a>
                    <a class="smhidden" href="/auth/register"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>注册</a>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>

        <!--<b-modal ref="searchModalRef" hide-footer title="全站搜索">-->
        <!-- 搜索弹窗 -->
        <div id="seacherModal" tabindex="-1">
            <div>
                <div class="next-seacher">
                    <div class="modal-body">
                        <form id="searchform" class="" role="search" method="get" action="/">
                            <input class="sb-search-input" placeholder="输入关键词查找..." type="text" value="" name="s" id="s" autofocus>
                            <input id="searchsubmit" class="sb-search-submit" type="submit" value="搜索">
                        </form>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
        <!--
        <b-btn variant="outline-danger" block @click="hideModal">关闭</b-btn>
        -->
        <!--</b-modal>-->

        <div>
            <div>
                <button @click="clickTest">点击</button>
            </div>
            <div>{{ title }}</div>
        </div>
    </div>
</template>

<script>
    // jquery is not supported by nashorn,so remove jquery
    // https://www.javaworld.com/article/2144908/scripting-jvm-languages/nashorn--javascript-made-great-in-java-8.html
    // var $ = require("jquery");

    // $(function () {
    //     // 手机端菜单点击展开
    //     $('.nav__trigger').on('click', function (e) {
    //         e.preventDefault();
    //         $(this).parent().toggleClass('nav--active');
    //     });
    // })

    export default {
        name: "Header",
        props: {
            title: {
                type: [String],
                default() {
                    return ''
                }
            }
        },
        methods: {
            clickTest() {
                alert(this.title)
            },
            showModal() {
                alert("showModal")
                // this.$refs.searchModalRef.show()
            },
            hideModal() {
                alert("hideModal")
                // this.$refs.searchModalRef.hide()
            }
        }
    }
</script>

<style>
    #header {
        background: #fff;
        height: 56px;
        line-height: 56px;
        box-shadow: 0 2px 6px 0 rgba(63, 74, 105, .16);
        margin-bottom: 20px;
    }

    /* logo */
    .logo {
        margin-top: 0;
        margin-bottom: 0;
        line-height: 75px;
        float: left;
    }

    .logo a {
        margin: 0;
        padding: 0;
    }

    .logo a img {
        margin-top: 8px;
        height: 40px;
    }

    /* 菜单 */
    .menu {
        float: left;
        margin-left: 45px;
        margin-right: 30px;
    }

    .header-right {
        float: right;
    }

    .next-menu li {
        float: left;
        position: relative;
    }

    .next-menu li a {
        color: #666;
        font-size: 15px;
        padding: 0 20px 0 15px;
    }

    .next-menu > li > a > i {
        margin-right: 2px;
    }

    .next-menu li .sub-menu {
        position: absolute;
        min-width: 150px;
        display: none;
        left: 0;
        top: 76px;
        line-height: 40px;
        box-shadow: 0 2px 6px 0 rgba(63, 74, 105, .16);
        background: #fff;
        z-index: 10;
    }

    .next-menu li .sub-menu li {
        border-bottom: 1px dashed #efefef;
        width: 100%;

    }

    .next-menu li .sub-menu li a {
        font-size: 13px;

    }

    .next-menu li:hover .sub-menu {
        display: block;
    }

    .next-menu li .sub-menu li:last-child {
        border-bottom: none;
    }

    .loginlink a {
        color: #666;
        margin-left: 15px;
        cursor: pointer;
        font-size: 15px;
    }

    .loginlink a i {
        margin-right: 5px;
        color: #333;
        font-size: 20px;
    }

    .loginlink a:hover {
        color: #333;
    }

    /* 搜索框 */
    #searchform {
        position: relative;
    }

    #searchform #s {
        width: 100%;
        padding: 10px;
        height: 45px;
        line-height: 45px;
        background: #fafafa;
        border: 1px solid #333;
        outline: none;
    }

    #searchform #searchsubmit {
        position: absolute;
        right: 0;
        top: 0;
        height: 45px;
        line-height: 42px;
        width: 100px;
        background: #333;
        border: 1px solid #333;
        color: #fff;
    }

    #searchCollapse {
        max-width: 1200px;
        margin: auto;
    }

    #seacherModal .next-seacher {
        border: none;
        background: #fff;
    }

    .modal-body {
        width: 98%;
        margin: 0 auto;
        padding: 15px 5px;
    }

    /* 手机菜单 */
    .phone-menu {
        height: 60px;
        display: none;
    }

    /*
      ##Device = Most of the Smartphones Mobiles (Portrait)
      ##Screen = B/w 320px to 479px
    */
    @media (min-width: 320px) and (max-width: 480px) {
        .phone-menu {
            display: block;
        }

        .next-menu {
            display: none;
        }
    }

    /* Don't nest if you don't have to. */
    /**
    * Style #1
    */
    .style-1 .nav {
        -webkit-transform: translateX(-100%);
        transform: translateX(-100%);
        width: 100%;
        height: 100%;
    }

    .style-1 .nav__link {
        opacity: 0;
        -webkit-transition-delay: 500ms;
        transition-delay: 500ms;
    }

    .style-1 .nav--active .nav__link {
        opacity: 1;
        font-size: 16px;
        line-height: 45px;
    }

    .style-1 .nav--active .nav {
        -webkit-transition: all 300ms ease-in-out;
        transition: all 300ms ease-in-out;
        -webkit-transform: translateX(0);
        transform: translateX(0);
    }

    .style-1 .nav--active .nav__icon {
        background: rgba(0, 0, 0, 0);
    }

    .style-1 .nav--active .nav__icon:before {
        margin-top: 0;
        -webkit-transform: rotate(45deg);
        transform: rotate(45deg);
    }

    .style-1 .nav--active .nav__icon:after {
        margin-top: 0;
        -webkit-transform: rotate(-45deg);
        transform: rotate(-45deg);
    }

    .iphone__screen {
        width: 100%;
        height: 55px;
        position: absolute;
        top: 0px;
        right: 0;
        left: 0;
        overflow: hidden;
    }

    /**
    * Navigation Defaults
    */
    .nav {
        position: absolute;
        z-index: 100;
        opacity: 0;
        -webkit-transition-property: all;
        transition-property: all;
        -webkit-transition-duration: 300ms;
        transition-duration: 300ms;
    }

    .nav--active {
        height: 100% !important;
    }

    .nav--active .nav {
        opacity: 1;
        background-color: #333;
    }

    .nav__list {
        margin: 0;
        padding-top: 30px;
    }

    .nav__list li {
        list-style-type: none;
        text-align: center;
    }

    .nav__list li a {
        font-size: 16px;
        text-transform: uppercase;
        text-decoration: none;
        color: #FFFFFF;
        opacity: 1;
        -webkit-transition: opacity 300ms ease-in-out;
        transition: opacity 300ms ease-in-out;
        line-height: 30px;
    }

    .nav__list .sub-menu li a {
        font-size: 14px;
        opacity: 0.8;
        margin-left: 15px;
    }

    .nav__list li a {
        line-height: 24px;
    }

    .nav__list li a i {
        margin-right: 5px;
    }

    /* Default navigation icon */
    .nav__trigger {
        display: block;
        position: absolute;
        width: 30px;
        height: 48px;
        left: 15px;
        top: 25px;
        z-index: 200;
    }

    .nav--active .nav__trigger {
        opacity: 0.5;
    }

    .nav--active .nav__icon {
        background: #fff;
    }

    .nav--active .nav__icon:before {
        background: #fff;
    }

    .nav--active .nav__icon:after {
        background: #fff;
    }

    .nav__icon {
        display: inline-block;
        position: relative;
        width: 25px;
        height: 3px;
        background-color: #333;
        -webkit-transition-property: background-color, -webkit-transform;
        transition-property: background-color, transform, -webkit-transform;
        -webkit-transition-duration: 300ms;
        transition-duration: 300ms;
    }

    .nav__icon:before,
    .nav__icon:after {
        content: '';
        display: block;
        width: 25px;
        height: 3px;
        position: absolute;
        background: #333;
        -webkit-transition-property: margin, -webkit-transform;
        transition-property: margin, transform;
        -webkit-transition-duration: 300ms;
        transition-duration: 300ms;
    }

    .nav__icon:before {
        margin-top: -9px;
    }

    .nav__icon:after {
        margin-top: 9px;
    }
</style>