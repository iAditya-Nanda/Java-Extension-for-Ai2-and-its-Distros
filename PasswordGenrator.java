package com.AdityaNanda.PasswordGenerator;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import java.util.Random;

@DesignerComponent(
        version = 2,
        description = "An extension to create a random password.Made by Aditya Nanda",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://i.ibb.co/P4J8fFT/1817-2.png")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "")
//Permissions
@UsesPermissions(permissionNames = "")

public class iPassGenerator extends AndroidNonvisibleComponent {

    //Activity and Context
    private Context context;
    private Activity activity;

    public iPassGenerator(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }
    @SimpleFunction(description = "Returns a Random String with special characters example:- GfVV<j&Dx9$(8o}Op%#e ,etc of any lenght you enter.NOTE:-Minimum Possible length is 4")
     public Object String generatePassword(int length, boolean haveLowerCase, boolean haveUpperCase, boolean haveSpecialCharacters) {

    StringBuilder password = new StringBuilder();

    char[] chars = new char[] {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    char[] specials = new char[] {
        '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '{', '}',
        ':', ';', '"', '\'', '<', '>', ',', '.', '?', '/', '~', '`'
    };

    StringBuilder combined = new StringBuilder();
    if (haveLowerCase) {
      for (char ch : chars) {
        combined.append(ch);
      }
    }
    if (haveUpperCase) {
      for (char ch : chars) {
        combined.append(Character.toUpperCase(ch));
      }
    }
    if (haveSpecialCharacters) {
      for (char ch : specials) {
        combined.append(ch);
      }
    }

    int len = combined.length();
    if (len == 0) {
      // just numbers
      for (int i = 0; i <= 9; i++)
        combined.append(i);
      len = combined.length();
    }
    char[] characters = combined.toString().toCharArray();

    Random random = new Random();
    for (int i = 0; i < length; i++) {
      password.append(characters[random.nextInt(len)]);
    }
    return password.toString();
  }
	}