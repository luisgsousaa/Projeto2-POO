package com.mycompany.projeto2.poo;

/**
 * Interface para poder implementar vida e as suas mecanicas tanto na cidade como nas unidades
 */
public interface ILife {
    int getLife();
    void setLife(int life);
    void takeDamage(int damage);
    void heal(int damage);
    boolean isAlive();
}
