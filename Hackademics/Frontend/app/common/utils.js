Array.prototype.pushArray = function () {
    this.push.apply(this, this.concat.apply([], arguments));
};

String.prototype.startsWith = function (prefix) {
    return this.indexOf(prefix) === 0;
};
