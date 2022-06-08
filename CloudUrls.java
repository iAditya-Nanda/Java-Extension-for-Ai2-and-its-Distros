package com.AdityaNanda.CloudinaryUrls;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

@DesignerComponent(
        version = 1,
        description = "An extension to generate URL from cloudinary public id (For Niotron) Made by Aditya Nanda",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "")
//Permissions
@UsesPermissions(permissionNames = "")

public class CloudURLs extends AndroidNonvisibleComponent {

    //Activity and Context
    private Context context;
    private Activity activity;

    public CloudURLs(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleFunction(description = "Genrate Url with public id \n Inputs for File Types: \n 0==>Image File \n 1==>Video File \n 2==>Other File")
    public Object Url(String cloudName, String publicId, int fileType) {
        
        String url = "";
        
        if(fileType == 0) {
            url = "https://res.cloudinary.com/" + cloudName + "/image/upload/" + publicId;
        }

        if(fileType == 1) {
            url = "https://res.cloudinary.com/" + cloudName + "/video/upload/" + publicId;
        }

        if(fileType == 2) {
            url = "https://res.cloudinary.com/" + cloudName + "/raw/upload/" + publicId;
        }
        System.out.print(url);
        return url;
    }
}
