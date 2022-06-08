package com.AdityanNanda.Typy;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import android.animation.ValueAnimator;
import android.os.Handler;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;

@DesignerComponent(
        version = 1,
        description = "Typing animation extension",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://i.ibb.co/P4J8fFT/1817-2.png")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "")
//Permissions
@UsesPermissions(permissionNames = "")

public class Typy extends AndroidNonvisibleComponent {

    //Activity and Context
    private Context context;
    private Activity activity;

    public Typy(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleEvent(description = "This event raises when any error occurs")
    public void Error(String error){
        EventDispatcher.dispatchEvent(this,"Error",error);
    }
    @SimpleEvent(description = "This event raises when counting finishes on given component")
    public void CountFinished(Component component){
        EventDispatcher.dispatchEvent(this,"CountFinished",component);
    }
    @SimpleEvent(description = "This event raises when text writing on given component finishes")
    public void TextFinished(Component component){
        EventDispatcher.dispatchEvent(this,"TextFinished",component);
    }

    @SimpleFunction(description = "This block helps you to set the integer in any label smoothly")
    public void CountOnLabel(final Label component,final long duration,final int start, final int end){
        try {
            final ValueAnimator animator=ValueAnimator.ofInt(start,end);
            animator.setDuration(duration);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    component.Text(animator.getAnimatedValue().toString());
                    if (end==Integer.parseInt(animator.getAnimatedValue().toString()))
                        CountFinished(component);
                }
            });
            animator.start();
        }catch (Exception e){
            this.Error(e.toString());
        }
    }
    @SimpleFunction(description = "This block helps you to set the text char by char on any label")
    public void SmoothTextOnLabel(final Label component,final long duration, final String text){
        if (duration > 0) {
        try {
            final char[] array = text.toCharArray();
            component.Text("");
            ValueAnimator valueAnimator=ValueAnimator.ofInt(0,array.length);
            valueAnimator.setDuration(duration);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    String str= text.substring(0,Integer.parseInt(valueAnimator.getAnimatedValue().toString()));
                    component.Text(str);
                    if (component.Text()==text)
                        TextFinished(component);
                }
            });
            valueAnimator.start();
            }catch(Exception e){
                this.Error(e.toString());
            }
        }else
            throw new YailRuntimeError("Invalid duration","duration is less than zero");
    }
    @SimpleFunction(description = "This block helps you to set the text char by char in any button")
    public void SmoothTextOnButton(final Button component, final long duration, final String text){
        if (duration > 0) {
            try {
                final char[] array = text.toCharArray();
                component.Text("");
                ValueAnimator valueAnimator=ValueAnimator.ofInt(0,array.length);
                valueAnimator.setDuration(duration);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        String str= text.substring(0,Integer.parseInt(valueAnimator.getAnimatedValue().toString()));
                        component.Text(str);
                        if (component.Text()==text)
                            TextFinished(component);
                    }
                });
                valueAnimator.start();
            }catch(Exception e){
                this.Error(e.toString());
            }
        }else
            throw new YailRuntimeError("Invalid duration","duration is less than zero");
    }
    @SimpleFunction(description = "This block helps you to set the integer in any Button smoothly")
    public void CountOnButton(final Button component,final long duration,final int start,final int end){
        try {
            final ValueAnimator animator=ValueAnimator.ofInt(start,end);
            animator.setDuration(duration);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    component.Text(animator.getAnimatedValue().toString());
                    if (end==Integer.parseInt(animator.getAnimatedValue().toString()))
                        CountFinished(component);
                }
            });
            animator.start();
        }catch (Exception e){
            this.Error(e.toString());
        }
    }
}