package com.gerenciadortarefas.adapter; // Define o pacote onde essa classe está localizada

import java.lang.reflect.Type; // Importa a classe Type, usada para representar tipos genéricos em tempo de execução
import java.time.LocalDate; // Importa a classe LocalDate, que representa uma data sem hora
import java.time.format.DateTimeFormatter; // Importa a classe DateTimeFormatter para formatar datas

import com.google.gson.JsonDeserializationContext; 
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Classe adaptadora para converter objetos LocalDate para JSON e vice-versa.
 * Implementa JsonSerializer (para serializar) e JsonDeserializer (para desserializar).
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // Definição de um formato padrão ISO para a data (yyyy-MM-dd)
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Método para serializar um objeto LocalDate em JSON.
     *
     * @param src       O objeto LocalDate que será convertido para JSON.
     * @param typeOfSrc O tipo do objeto a ser serializado (não utilizado diretamente aqui).
     * @param context   O contexto da serialização.
     * @return Um JsonElement contendo a data formatada como String.
     */
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formatter)); // Converte LocalDate para String e retorna como JsonPrimitive
    }

    /**
     * Método para desserializar um JSON em um objeto LocalDate.
     *
     * @param json    O elemento JSON contendo a data como String.
     * @param typeOfT O tipo do objeto esperado (LocalDate, neste caso).
     * @param context O contexto da desserialização.
     * @return Um objeto LocalDate representando a data do JSON.
     * @throws JsonParseException Se a conversão falhar.
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(), formatter); // Converte a String do JSON para um objeto LocalDate
    }
}
