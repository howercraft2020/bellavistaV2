package cl.clsoft.bave.view;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import cl.clsoft.bave.model.MtlPhysicalInventoryTags;

public class FragmentPagerAdapterTags extends FragmentPagerAdapter {

    private static final String TAG = "FPAdapterTags";
    private static int NUM_ITEMS = 2;
    private AdapterInventarioFisicoDetalle adapterTagsInventariados = new AdapterInventarioFisicoDetalle();
    private AdapterInventarioFisicoDetalleNoInventariado adapterTagsNoInventariados = new AdapterInventarioFisicoDetalleNoInventariado();
    FragmentTagsInventariados fragmentTagsInventariados = null;
    FragmentTagsNoInventariados fragmentTagsNoInventariados = null;


    public FragmentPagerAdapterTags(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public void setTagsInventariados(List<MtlPhysicalInventoryTags> tags) {
        //adapterTagsInventariados = new AdapterInventarioFisicoDetalle(tags);
        adapterTagsInventariados.setTags(tags);
    }

    public void setTagsNoInventariados(List<MtlPhysicalInventoryTags> tags) {
        //adapterTagsNoInventariados = new AdapterInventarioFisicoDetalleNoInventariado(tags);
        adapterTagsNoInventariados.setTags(tags);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragmentTagsInventariados = new FragmentTagsInventariados(adapterTagsInventariados);
                return fragmentTagsInventariados;
            case 1:
                fragmentTagsNoInventariados = new FragmentTagsNoInventariados(adapterTagsNoInventariados);
                return fragmentTagsNoInventariados;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount");
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Inventario";
            case 1:
                return "Pendientes";
            default:
                return null;
        }
    }
}
