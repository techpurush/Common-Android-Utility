package com.techpurush.commonandroidutility.Slider;

import android.annotation.SuppressLint;

import androidx.viewpager2.widget.ViewPager2;

import com.techpurush.commonandroidutility.R;
import com.techpurush.commonandroidutility.Slider.transformers.AntiClockSpinTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.ClockSpinTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.CubeInDepthTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.CubeInRotationTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.CubeOutDepthTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.CubeOutRotationTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.CubeOutScalingTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.DepthPageTransformer;
import com.techpurush.commonandroidutility.Slider.transformers.DepthTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.FadeOutTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.FanTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.GateTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.HingeTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.HorizontalFlipTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.PopTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.SimpleTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.SpinnerTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.TossTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.VerticalFlipTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.VerticalShutTransformation;
import com.techpurush.commonandroidutility.Slider.transformers.ZoomOutPageTransformer;


public class Utils {
    @SuppressLint("NewApi")
    public static ViewPager2.PageTransformer getTransformer(int id) {
        if (id == R.id.action_anti_clock_spin) {
            return new AntiClockSpinTransformation();
        } else if (id == R.id.action_clock_spin) {
            return new ClockSpinTransformation();
        } else if (id == R.id.action_cube_in_depth) {
            return new CubeInDepthTransformation();
        } else if (id == R.id.action_cube_in_rotate) {
            return new CubeInRotationTransformation();
        } else if (id == R.id.action_cube_out_depth) {
            return new CubeOutDepthTransformation();
        } else if (id == R.id.action_cube_out_rotate) {
            return new CubeOutRotationTransformation();
        } else if (id == R.id.action_cube_out_scaling) {
            return new CubeOutScalingTransformation();
        } else if (id == R.id.action_depth_page) {
            return new DepthPageTransformer();
        } else if (id == R.id.action_depth) {
            return new DepthTransformation();
        } else if (id == R.id.action_fade_out) {
            return new FadeOutTransformation();
        } else if (id == R.id.action_fan) {
            return new FanTransformation();
        } else if (id == R.id.action_gate) {
            return new GateTransformation();
        } else if (id == R.id.action_hinge) {
            return new HingeTransformation();
        } else if (id == R.id.action_horizontal_flip) {
            return new VerticalFlipTransformation();
        } else if (id == R.id.action_pop) {
            return new PopTransformation();
        } else if (id == R.id.action_simple_transformation) {
            return new SimpleTransformation();
        } else if (id == R.id.action_spinner) {
            return new SpinnerTransformation();
        } else if (id == R.id.action_toss) {
            return new TossTransformation();
        } else if (id == R.id.action_vertical_flip) {
            return new HorizontalFlipTransformation();
        } else if (id == R.id.action_vertical_shut) {
            return new VerticalShutTransformation();
        } else if (id == R.id.action_zoom_out) {
            return new ZoomOutPageTransformer();
        }

        return null;
    }
}
