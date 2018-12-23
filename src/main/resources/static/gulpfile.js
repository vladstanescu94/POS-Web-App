var gulp = require('gulp');
var data = require('gulp-data');
var stylus = require('gulp-stylus');
var log = require('fancy-log');
let cleanCSS = require('gulp-clean-css');
var rename = require("gulp-rename");

// include, if you want to work with sourcemaps
var sourcemaps = require('gulp-sourcemaps');

gulp.task('stylus', function () {
    return gulp.src('src/stylus/*.styl')
        .pipe(sourcemaps.init())
        .pipe(stylus())
        .on('end', function(){ log('Almost there...'); })
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest('build/css'))
        .on('end', function(){ log('Done Styling!'); });
});

gulp.task('minify-css',function () {
    return gulp.src('build/css/main.css')
      .pipe(sourcemaps.init())
      .pipe(cleanCSS())
      .pipe(rename('main.min.css'))
      .pipe(sourcemaps.write())
      .pipe(gulp.dest('build/css'))
      .on('end', function(){ log('Done Minifying!'); });
});

gulp.task('watch', function () {
    gulp.watch('src/stylus/**/*.styl', ['stylus']);
    gulp.watch('build/css/main.css', ['minify-css']);
});

gulp.task('default', ['stylus', 'minify-css', 'watch']);