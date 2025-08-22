package com.lalit.dto;

import java.util.List;

public record Team(Integer id, String name, List<Player> players) {
}
