package com.starkwiz.starkwiz.LinkingClass;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;



public class AlertBoxClasses {

    Context context;


    public static void SimpleAlertBox(Context context,String Message){

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setMessage(Message)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert11 = alertDialog.create();
        alert11.show();
    }

//    public static void NoUserFoundAlertBox(final Context context, String Message){
//
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
//                .setMessage(Message)
//                .setPositiveButton("Register as New User", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(context, MainActivity.class);
//                        context.startActivity(intent);
//                    }
//                });
//            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.cancel();
//                }
//            });
//        AlertDialog alert11 = alertDialog.create();
//        alert11.show();
//    }
//
//    public static void Logout(final Context context){
//
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
//                .setMessage("Are you sure you want to Logout?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        SharedPrefManager.getInstance(context).logout();
//                        Intent intent = new Intent(context, SignInActivity.class);
//                        context.startActivity(intent);
//                    }
//                });
//        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });
//        AlertDialog alert11 = alertDialog.create();
//        alert11.show();
//    }
//
//    public static void AppExit(final Context context){
//
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
//                .setMessage("Are you sure you want to Exit?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                });
//        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.cancel();
//            }
//        });
//        AlertDialog alert11 = alertDialog.create();
//        alert11.show();
//    }
}
