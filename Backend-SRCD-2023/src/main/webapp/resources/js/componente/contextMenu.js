/* global PrimeFaces
 * 
 *  Correção para ContextMenu que fecha sozinho 
 *  após receber um update
 */
var eventCM;
PrimeFaces.widget.ContextMenu.prototype.show = function (event) {
    // Esconde outros Context Menus
    $(document.body).children('.ui-contextmenu:visible').hide();
    if (event) {
        eventCM = event;
    }
    var win = $(window);
    var left = event.pageX;
    var top = event.pageY;
    var width = this.jq.outerWidth();
    var height = this.jq.outerHeight();
    // Detecção de colisão para limites de janela
    if ((left + width) > (win.width()) + win.scrollLeft()) {
        left = left - width;
    }
    if ((top + height) > (win.height() + win.scrollTop())) {
        top = top - height;
    }
    if (this.cfg.beforeShow) {
        this.cfg.beforeShow.call(this);
    }
    this.jq.css({"left": left, "top": top, "z-index": ++PrimeFaces.zindex}).show();
    this.bindPanelEvents();
    event.preventDefault();
};