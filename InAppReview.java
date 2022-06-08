package com.AdityaNanda.inAppReview;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

@DesignerComponent(
        version = 1,
        description = "Extension for in-App-Review , with this extension you can get review of you app that is uploaded on playstore without closing app.Made by Aditya Nanda Aditya O.P.C. LTD.",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "https://i.ibb.co/P4J8fFT/1817-2.png"
        )

@SimpleObject(external = true)
@UsesLibraries(libraries = "playcore.jar")

public class InAppReview extends AndroidNonvisibleComponent {
    public static ComponentContainer container;

    public InAppReview (final ComponentContainer container) {
        super(container.$form());
        InAppReview.container = container;
    }

    @SimpleFunction (description = "Ask the user to review the app.")
    public void Ask() {
        final ReviewManager manager = ReviewManagerFactory.create(this.form.$context());
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    ReviewInfo reviewInfo = task.getResult();
                    Task<Void> flow = manager.launchReviewFlow(container.$form(), reviewInfo);
                    flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            Review ();
                        }
                    });
                } else {
                    Error();
                }
            }
        });
    }

    @SimpleEvent (description = "The flow has finished. The event does not indicate whether the user reviewed or not, or even whether the review dialog was shown. Thus, no matter the result, we continue our app flow.")
    public void Review() {
        EventDispatcher.dispatchEvent(this, "Finished");
    }

    @SimpleEvent (description = "An Error Occurred")
    public void Error() {
        EventDispatcher.dispatchEvent(this, "Error");
    }

}
