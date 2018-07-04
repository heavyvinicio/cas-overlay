//滑动解锁插件
//2017年2月16日 作者:holy_nova
;
(function($) {
    function Slider(elem, options) {
        this.$container = elem;
        this.default = {
            width: this.$container.width() - 2,
            height: this.$container.height() - 2,
            bgColor: '#E8E8E8',
            progressColor: '#FFE97F',
            handleColor: '#fff',
            succColor: '#78D02E',
            text: 'slide to unlock',
            succText: 'ok!',
            textColor: '#000',
            succTextColor: '#000',
            successFunc: function() {
                alert('successfully unlock!');
            }
        };
        this.options = $.extend({}, this.default, options);
        this.isSuccess = false;
    }
    Slider.prototype = {
        create: function() {
            var $container = this.$container;
            var options = this.options;
            initDOM();
            initStyle();

            function initDOM() {
                var template =
                    '<div class="slide-to-unlock-bg">' +
                    '   <span class="title">' + options.text + '</span>' +
                    '</div>' +
                    '<div class="slide-to-unlock-progress"></div>' +
                    '<div class="slide-to-unlock-handle">' +
                    '   <span class="arrow"> >> </span>   ' +
                    '</div>'+
                    '<div class="slide-to-unlock-flash">' +
                    '   <div class="flash">' +
                    '       <i class="iconFlash iconfont icon-shuangyoujiantou-"></i>' +
                    '   </div>' +
                    '</div>';
                $container.html(template);
            }

            function initStyle() {
                $container.css({
                    position: 'relative',
                });
                $container.find('.title').css({
                    lineHeight: options.height + 'px',
                    fontSize: options.height / 3.5,
                    color: options.textColor
                });
                $container.find('.slide-to-unlock-bg').css({
                    width: options.width + 'px',
                    height: options.height + 'px',
                    backgroundColor: options.bgColor,
                });
                $container.find('.slide-to-unlock-progress').css({
                    backgroundColor: options.progressColor,
                    height: options.height - 2 + 'px'
                });
                $container.find('.slide-to-unlock-handle').css({
                    backgroundColor: options.handleColor,
                    height: (options.height - 0) + 'px',
                    lineHeight: (options.height - 0) + 'px',
                    width: (Math.floor(options.width / 8)) + 'px',
                });
                $container.find('.slide-to-unlock-handle span').css({
                    position: 'relative',
                    margin: '9px',
                    top: '-2px',
                    color: 'rgba(0, 0, 0, 0.4)',
                    padding: '1px'
                });
                $container.find('.slide-to-unlock-flash').css({
                    width: options.width + 'px',
                    height: options.height + 'px',
                    overflow: 'hidden',
                    float: 'right'
                });
            }
        },
        bindDragEvent: function() {
            var that = this;
            var $container = this.$container;
            var options = this.options;
            var downX;
            var $prog = $container.find('.slide-to-unlock-progress'),
                $bg = $container.find('.slide-to-unlock-bg'),
                $handle = $container.find('.slide-to-unlock-handle'),
                $flashbg  = $container.find('.slide-to-unlock-flash'),
                $arrow   = $container.find('.arrow');
            var succMoveWidth = $bg.width() - $handle.width();
            $handle.on('mousedown', null, mousedownHandler);

            function getLimitNumber(num, min, max) {
                if (num > max) {
                    num = max;
                } else if (num < min) {
                    num = min;
                }
                return num;
            }

            function mousedownHandler(event) {
                downX = event.clientX;
                $(document).on('mousemove', null, mousemoveHandler);
                $(document).on('mouseup', null, mouseupHandler);
            }

            function mousemoveHandler(event) {
                var moveX = event.clientX;
                var diffX = getLimitNumber(moveX - downX, 0, succMoveWidth);
                $prog.width(diffX);

                $flashbg.width(succMoveWidth - diffX);

                $handle.css({
                    left: diffX
                });

                if (diffX === succMoveWidth) {
                    success();
                }
                event.preventDefault();
            }

            function mouseupHandler(event) {
                if (!that.isSuccess) {
                    $prog.animate({
                        width: 0
                    }, 100);
                    $handle.animate({
                        left: 0
                    }, 100);
                    $flashbg.animate({
                        width:options.width
                    }, 100)
                }
                $(document).off('mousemove', null, mousemoveHandler);
                $(document).off('mouseup', null, mouseupHandler);
            }

            function success() {
                $prog.css({
                    backgroundColor: options.succColor,
                });

                $container.find('.title').css({
                    color: options.succTextColor
                });

                $arrow.html('');
                $arrow.addClass('iconArrow iconfont icon-tongguo');


                that.isSuccess = true;

                $container.find('.title').html(options.succText);

                $handle.off('mousedown', null, mousedownHandler);

                $(document).off('mousemove', null, mousemoveHandler);

                setTimeout(function() {
                    options.successFunc && options.successFunc();
                }, 30);
            }
        }
    };
    $.fn.extend({
        slideToUnlock: function(options) {
            return this.each(function() {
                var slider = new Slider($(this), options);
                slider.create();
                slider.bindDragEvent();
            });
        }
    });
})(jQuery);
