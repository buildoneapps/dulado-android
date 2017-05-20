package com.buildone.dulado.contracts;

import com.buildone.dulado.model.ProductObject;
import com.buildone.dulado.view.BaseView;

import java.util.ArrayList;

/**
 * Created by Alessandro Pryds on 02/05/2017.
 */

public interface AddProductContract {
    interface View extends BaseView {
        void initToolbar();
        void checkPermissions();
        void initPhotosRecyclerView(ArrayList<String> items);
        void showPhotoChooserDialog();
        void openCamera();
        void openGallery();
        void navigateToProductPreview(ProductObject product);
        void navigateToProductPage(ProductObject product);
        void navigateToShareActivity(ProductObject product);

    }

    interface Presenter{
        void start();
        void initSubscriptions();
        void disposeAll();
        void onButtonAddPhotoTouched(int position);
        void onButtonPreviewTouched();
        void onButtonCameraTouched();
        void onButtonGalleryTouched();
        void onPermissionGranted();
        void onPermissionError();
    }
}
