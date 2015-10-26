package org.apx.scf.config.util.jpa;

import java.util.UUID;
import java.util.Vector;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sessions.Session;


public class UUIDSequence extends Sequence {

    private static final long serialVersionUID = -3184457810264720441L;

    public UUIDSequence() {
        super();
    }

    public UUIDSequence(String name) {
        super(name);
    }

    @Override
    public Object getGeneratedValue(Accessor accessor,
                                    AbstractSession writeSession, String seqName) {
        return UUID.randomUUID().toString().toUpperCase();
    }

    @Override
    public Vector getGeneratedVector(Accessor accessor,
                                     AbstractSession writeSession, String seqName, int size) {
        Vector v = new Vector();
        int i = 0;
        while (i < size) {
            v.add(getGeneratedValue(accessor, writeSession, seqName));
        }
        return v;
    }

    @Override
    public void onConnect() {
    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public boolean shouldAcquireValueAfterInsert() {
        return false;
    }

    @Override
    public boolean shouldUseTransaction() {
        return false;
    }

    @Override
    public boolean shouldUsePreallocation() {
        return false;
    }

}
