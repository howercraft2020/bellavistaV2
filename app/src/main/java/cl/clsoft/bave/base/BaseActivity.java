package cl.clsoft.bave.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cl.clsoft.bave.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {

    protected Presenter mPresenter;
    protected static final String APP = "INVENTARIO";
    protected View llProgressBar;
    protected TextView pbText;

    @NonNull
    protected abstract Presenter createPresenter(@NonNull final Context context);
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void showProgres() {
        if (this.llProgressBar != null){
            TextView tvMensaje = this.llProgressBar.findViewById(R.id.pbText);
            tvMensaje.setText("Por favor espere ...");
            this.llProgressBar.setVisibility(View.VISIBLE);

        }
    }

    public void showProgres(String mensaje) {
        if (this.llProgressBar != null){
            TextView tvMensaje = this.llProgressBar.findViewById(R.id.pbText);
            tvMensaje.setText(mensaje);
            this.llProgressBar.setVisibility(View.VISIBLE);

        }
    }

    public void hideProgres() {
        if (this.llProgressBar != null)
            this.llProgressBar.setVisibility(View.GONE);
    }

    public void showWarning(String mensaje) {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Información")
                .setContentText(mensaje)
                .show();
    }

    public void showError(String mensaje) {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText(mensaje)
                .show();
    }

    public void showSuccess(String mensaje) {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Éxito")
                .setContentText(mensaje)
                .show();
    }

}
