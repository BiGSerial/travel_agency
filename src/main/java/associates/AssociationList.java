package main.java.associates;

import java.util.ArrayList;
import java.util.List;

public class AssociationList {
    private AssociationNode prim, ulti;

    public AssociationList() {
        this.prim = this.ulti = null;
    }

    public void adicionar(int tripId, int travelerId) {
        AssociationNode newNode = new AssociationNode(tripId, travelerId);
        if (ulti == null) {
            prim = ulti = newNode;
        } else {
            ulti.setNext(newNode);
            newNode.setPrev(ulti);
            ulti = newNode;
        }
    }

    public void remover(int tripId, int travelerId) {
        AssociationNode atual = prim;
        while (atual != null) {
            if (atual.getTripId() == tripId && atual.getTravelerId() == travelerId) {
                if (atual.getPrev() != null) {
                    atual.getPrev().setNext(atual.getNext());
                } else {
                    prim = atual.getNext();
                }
                if (atual.getNext() != null) {
                    atual.getNext().setPrev(atual.getPrev());
                } else {
                    ulti = atual.getPrev();
                }
                return;
            }
            atual = atual.getNext();
        }
    }

    public List<Integer> buscarViajantesPorViagem(int tripId) {
        List<Integer> idsViajantes = new ArrayList<>();
        AssociationNode atual = prim;
        while (atual != null) {
            if (atual.getTripId() == tripId) {
                idsViajantes.add(atual.getTravelerId());
            }
            atual = atual.getNext();
        }
        return idsViajantes;
    }

    public List<Integer> buscarViagensPorViajante(int travelerId) {
        List<Integer> idsViagens = new ArrayList<>();
        AssociationNode atual = prim;
        while (atual != null) {
            if (atual.getTravelerId() == travelerId) {
                idsViagens.add(atual.getTripId());
            }
            atual = atual.getNext();
        }
        return idsViagens;
    }
}
