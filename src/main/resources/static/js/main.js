$(function() {
    $('.date').each(function() {
        $(this).datepicker();
    });
});

const auth = new Vue ({
    el: '.auth-form',
    data: {
        login: null,
        password: null,
        error: null,
        hasError: false
    },
    methods: {
        checkForm: function(e) {
            e.preventDefault();
            this.hasError = false;
            this.error = null;

            if (!this.validPassword(this.password)) this.error = 'Длина пароля меньше 6 символов';
            if (!this.validLogin(this.login)) this.error = 'Неверный формат логина';

            if (this.error != null) this.hasError = true;
        },
        validLogin: function(login) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(login);
        },
        validPassword: function(password) {
            if (password == null || password.length < 6) return false;
            return true;
        },
        inputForm: function() {
            this.hasError = false;
        }
    }
});

const reg = new Vue ({
    el: '.reg-form',
    data: {
        email: null,
        emailError: null,
        hasEmailError: false,
        fullName: null,
        fullNameError: null,
        hasFullNameError: false,
        login: null,
        loginError: null,
        hasLoginError: false,
        password: null,
        passwordError: null,
        hasPasswordError: false,
        repeatPassword: null,
        phone: null,
        position: null,
        positionError: null,
        hasPositionError: false
    },
    methods: {
        checkForm: function (e) {
            e.preventDefault();
            this.emailError = null;
            this.hasEmailError = false;
            this.fullNameError = null;
            this.hasFullNameError = false;
            this.loginError = null;
            this.hasLoginError = false;
            this.passwordError = null;
            this.hasPasswordError = false;
            this.repeatPasswordError = null;
            this.positionError = null;
            this.hasPositionError = false;

            if (!this.validEmail(this.email)) this.emailError = 'Неверный формат E-mail';
            if (!this.validOnEmpty(this.fullName)) this.fullNameError = 'Поле не может быть пустым';
            if (!this.validOnEmpty(this.login)) this.loginError = 'Поле не может быть пустым';
            if (!this.validPassword(this.password)) this.passwordError = 'Длина пароля меньше 6 символов';
            if (!this.validRepeatPassword(this.repeatPassword)) this.passwordError = 'Пароли не совпадают';
            if (!this.validOnEmpty(this.position)) this.positionError = 'Поле не может быть пустым';

            if (this.emailError != null) this.hasEmailError = true;
            if (this.fullNameError != null) this.hasFullNameError = true;
            if (this.loginError != null) this.hasLoginError = true;
            if (this.passwordError != null) this.hasPasswordError = true;
            if (this.positionError != null) this.hasPositionError = true;
        },
        validEmail: function (email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        },
        validPassword: function (password) {
            if (password == null || password.length < 6) return false;
            return true;
        },
        validRepeatPassword: function (repeatPassword) {
            if (repeatPassword !== this.password) return false;
            return true;
        },
        validOnEmpty: function (value) {
            return value !== null ? true : false;
        },
        inputForm: function () {
            this.emailError = null;
            this.hasEmailError = false;
            this.fullNameError = null;
            this.hasFullNameError = false;
            this.loginError = null;
            this.hasLoginError = false;
            this.passwordError = null;
            this.hasPasswordError = false;
            this.repeatPasswordError = null;
            this.positionError = null;
            this.hasPositionError = false;
        }
    }
});

const socials = new Vue ({
    el: '.socials',
    data: {
        socials: [
            {className: 'vk', faClassName: 'vk'},
            {className: 'ok', faClassName: 'odnoklassniki'},
            {className: 'fb', faClassName: 'facebook'},
            {className: 'g-plus', faClassName: 'google-plus-g'},
            {className: 'yandex', faClassName: 'yandex'}
        ]
    }
});

const notify = new Vue({
    el: '.header__notify',
    data: {
        show: false,
        alertCount: 3
    },
    methods: {
        alertClose: function(e) {
            var alert = e.target.parentElement.closest('.header__alert');
            document.querySelector('.header__notify-content').removeChild(alert);
            if (this.alertCount == 1) document.querySelector('.header__notify-content').style.display = 'none';
            this.alertCount--;

            if (this.alertCount == 0) this.show = false;
        }
    }
});

const search = new Vue({
    el: '.header__search',
    data: {
        activeSearch: false
    }
});

const addSample = new Vue({
    el: '.add-sample',
    data: {
        selected: ''
    },
    computed: {
        isPostmortem: function() {
            return this.selected == '5' ? true : false;
        }
    }
});

const samples = new Vue({
    el: '.samples',
    data: {
        filter: false
    }
});

const loading = new Vue({
    el: '.loading',
    data: {
        showPreloader: false
    },
    mounted: function() {
        this.showPreloader = true;
        var self = this;
        setTimeout(function() {
            self.showPreloader = false;
        }, 1000);
    }
});
