require.config({
    baseUrl: '/js',
    paths: {
        'jquery': 'lib/jquery-1.12.3.min',
        'weui': 'lib/jqueryweui/jquery-weui.min',
        'fastclick': 'lib/fastclick'
    },
    shim: {
        'weui': {
            deps: ['jquery']
        }
    }
});