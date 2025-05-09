package com.Admin;

import java.util.HashMap;

public class ControleLogin {
    public static final int CODIGO_ADM = 1;
    public static final int CODIGO_MOD = 2;
    public static final int CODIGO_FUN = 3;

    private final HashMap<Integer, String> cargos = new HashMap<>();

    public ControleLogin() {
        cargos.put(CODIGO_ADM, "Cargo Administrador");
        cargos.put(CODIGO_MOD, "Cargo Moderador");
        cargos.put(CODIGO_FUN, "Cargo Funcionário");
    }

    public String getCargo(int codigo) {
        return cargos.getOrDefault(codigo, "Cargo não encontrado");
    }
}
