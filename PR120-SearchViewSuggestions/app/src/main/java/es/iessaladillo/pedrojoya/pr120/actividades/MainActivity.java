package es.iessaladillo.pedrojoya.pr120.actividades;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import es.iessaladillo.pedrojoya.pr120.R;
import es.iessaladillo.pedrojoya.pr120.datos.InstitutoHelper;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private SearchView svBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // La toolbar actuará como action bar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_burger);
        setSupportActionBar(mToolbar);
        // Se inicializa la base de datos (para que se carguen los datos iniciales).
        initBD();
    }

    // Abre y ceirra la base de datos para que se carguen los datos iniciales.
    private void initBD() {
        InstitutoHelper helper = new InstitutoHelper(getApplicationContext());
        SQLiteDatabase bd = helper.getWritableDatabase();
        bd.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        // Se obtiene y configura el SearchView en base al archivo XML de configuración.
        svBuscar = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.mnuBuscar));
        SearchManager gestorBusquedas = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        svBuscar.setSearchableInfo(gestorBusquedas.getSearchableInfo(
                new ComponentName(this, BusquedaActivity.class)));
        // Se crea y establece el Listener para cuando se expande o contrae el searchview.
/*
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.mnuBuscar),
                new MenuItemCompat.OnActionExpandListener() {
                    // Cuando se expande.
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        mToolbar.setNavigationIcon(R.drawable.ic_back);
                        return true;
                    }
                    // Cuando se colapsa.
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        mToolbar.setNavigationIcon(R.drawable.ic_burger);
                        return true;
                    }
                });
*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Se colapsa el searchview.
            svBuscar.onActionViewCollapsed();
            return true;
        }
        if (id == R.id.mnuBuscar) {
            // Se consume el evento mostrando el actionview correspondiente al SearchView.
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
