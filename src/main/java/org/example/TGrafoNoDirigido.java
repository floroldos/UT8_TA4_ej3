package org.example;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoKevinBacon {
protected TAristas lasAristas = new TAristas() ;
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }
    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public int numBacon(Comparable actor) {
        if(this.getVertices().get(actor) != null){
            TVertice v = this.getVertices().get(actor);
            return v.baconNumber(actor, this.getVertices().get("Kevin_Bacon"));
        }

        return -1;
    }

    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen)
    {
        if(this.getVertices().get(etOrigen) != null){ //Me fijo si el vertice existe
            desvisitarVertices();
            LinkedList<TVertice> resultado = new LinkedList<>();
            int[] contador = new int[2];
            this.getVertices().get(etOrigen).puntosArticulacion(resultado, contador);
            return resultado;
        }
        return null;
    }
}
