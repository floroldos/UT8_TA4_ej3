package org.example;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class TVertice<T> implements IVertice, IVerticeKevinBacon {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private int numBacon = 0;
    private T datos;
    private int numBp = 0;
    private int numBajo = 0;
    private int contador = 0;



    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        numBacon = 0;
        numBp = 0 ;
        numBajo = 0;
        contador = 0;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }


    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }


    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }


    @Override
    public void bpf(Collection<TVertice> visitados) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int bea() {
        int numKevin = 0;
        Queue<TVertice> queue = new LinkedList<>();
        this.setVisitado(true);
        queue.add(this);

        while (!queue.isEmpty()) {
            int verticesEnNivel = queue.size();
            TVertice x = queue.poll();
            LinkedList<TAdyacencia> adyacencias = x.getAdyacentes();

            System.out.println(adyacencias);

            for (TAdyacencia y : adyacencias) {
                TVertice j = y.getDestino();
                if (!j.getVisitado()) {
                    j.setVisitado(true);
                    queue.add(j);
                }
            }
        }
        return numKevin;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean conectadoCon(TVertice destino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int baconNumber(Comparable actor, TVertice kevinBacon) {
        Queue<TVertice> queue = new LinkedList<>();
        this.setVisitado(true);
        queue.add(this);
        while (!queue.isEmpty()) {
            int verticesEnNivel = queue.size();
            for (int i = 0; i < verticesEnNivel; i++) {
                TVertice vertice = queue.poll();
                if (vertice.equals(kevinBacon)) {
                    return numBacon;
                }
                LinkedList<TAdyacencia> x = vertice.getAdyacentes();
                for (TAdyacencia adyacencia : x) {
                    TVertice adyacente = adyacencia.getDestino();
                    if (!adyacente.getVisitado()) {
                        adyacente.setVisitado(true);
                        queue.add(adyacente);
                    }
                }
            }
            numBacon++;
        }
        return numBacon;
    }

    public void puntosArticulacion(Collection<TVertice> puntos, int[] contador) {
        contador[0]++;
        this.numBp = contador[0];
        this.numBajo = contador[0];
        boolean esPuntoArticulacion = false;
        int cantidadHijos = 0;

        this.setVisitado(true);

        for (TAdyacencia adyacentes : this.adyacentes) {
            TVertice adyacente = adyacentes.getDestino();
            if (!adyacente.getVisitado()) {
                adyacente.puntosArticulacion(puntos, contador);
                cantidadHijos++;
                this.numBajo = Math.min(this.numBajo, adyacente.numBajo);
                if (adyacente.numBajo >= this.numBp) {
                    esPuntoArticulacion = true;
                }
            } else {
                this.numBajo = Math.min(this.numBajo, adyacente.numBp);
            }
        }

        if (this.numBp > 1) {
            if (esPuntoArticulacion) {
                puntos.add(this);
            }
        } else {
            if (cantidadHijos > 1) {
                puntos.add(this);
            }
        }
    }

    @Override
    public String toString(){
        return (String) this.getEtiqueta();
    }


    /*
    contador ++
    this.numBp <- contador[0]
    this.numBajo <- contador[0]

    PARACADA adyacencia en this.adyacencias
        adyacente <- adyacencia.destino
        SI no(adyacente.visitado) ENTONCES
            adyacente.puntosArticulacion(puntos, contador)
            hijos.add(adyacente)
            this.numBajo <- mínimoEntre (this.numBajo, adyacente.numBajo)
        SINO
            this.numBajo <- mínimoEntre (this.numBajo, adyacente.numBp)
    FIN PARA CADA
    SI this.numBp > 1 ENTONCES
        PARACADA hijo en hijos HACER
            SI hijo.numBajo ≥ this.numBp ENTONCES
                puntos.add(this)
            FIN SI
        FIN PARACADA
    SINO
        SI hijos.largo > 1 ENTONCES
            puntos.add(this)
        FIN SI
    FIN SI
    FIN
     */

    @Override
    public int getBacon() {
        return numBacon;
    }

    @Override
    public void setBacon(int newBacon) {
        numBacon = newBacon;
    }

    public int getNumBp() {
        return numBp;
    }
    public void setNumBp(int numBp) {
        this.numBp = numBp;
    }
    public int getNumBajo() {
        return numBajo;
    }
    public void setNumBajo(int numbajo) {
        this.numBajo = numbajo;
    }


}
