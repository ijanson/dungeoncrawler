import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		int n = 0;
		int maxHealth = 10; // will increase with upgrades
		Random random = new Random();
		String response;
		String dialogue;
		boolean newRoom = true;
		boolean fight = false;
		boolean first = true;
		Self player = new Self();
		World world = new World();
		world.generate();
		Scanner in = new Scanner(System.in);
		player.addItem(new Item("Fists", 1, -1, 3));
        dialogue = "You wake up in a dimly lit room, lying on the ground\nThere is a sword lying in the corner, and a door to the north\n";
		while (true) {
			if (world.getActiveRoom().getEnemy() != null) {
				fight = true;
				if (world.getX() == 4 && world.getY() == 0) {
					System.out.println("The final battle");
				} else if (world.getActiveRoom().getBoss()) {
					System.out.println("Boss fight");
				} else {
					System.out.println("As you walked into the next room, you were attacked by a monster");
				}
				while (fight) {
					if (!first) {
						player.setHp(player.getHp() - world.getActiveRoom().getEnemy().getDmg());
					}
					if (player.getHp() == 0) {
						System.out.println("You are dead");
						System.exit(0);
					}
					System.out.println("You - HP: " + player.getHp());
					System.out.println("Monster - ATK: " + world.getActiveRoom().getEnemy().getDmg() + " - HP: " + world.getActiveRoom().getEnemy().getHp());
					System.out.print("> ");
					response = in.nextLine();
					switch (response.toLowerCase()) {
						case "help":
							System.out.println("Commands:\nAttack\nUse");
							break;
						case "attack":
							System.out.println("Weapons:");
							for(Item item : player.getInventory()) {
								if (item.getType() == 1) {
									System.out.print(item.getName());
									System.out.println(" - ATK: " + item.getHealth() + " - DUR: " + item.getDurability());
								} else if (item.getType() == 3) {
									System.out.print(item.getName());
									System.out.println(" - ATK: " + item.getHealth());
								}
							}
							while (true) {
								System.out.print("> ");
								response = in.nextLine();
								if (player.getNames().indexOf(response) != -1 && (player.getInventory().get(player.getNames().indexOf(response)).getType() == 1 || player.getInventory().get(player.getNames().indexOf(response)).getType() == 3)) {
									world.getActiveRoom().getEnemy().setHp(world.getActiveRoom().getEnemy().getHp() - player.getInventory().get(player.getNames().indexOf(response)).getHealth());
									player.getInventory().get(player.getNames().indexOf(response)).setDurability(player.getInventory().get(player.getNames().indexOf(response)).getDurability() - 1);
									System.out.println("Attacked with " + response);
									if (player.getInventory().get(player.getNames().indexOf(response)).getDurability() == 0) {
										System.out.println(response + " broke");
										player.removeItem(player.getInventory().get(player.getNames().indexOf(response)));
									}
									break;
								} else {
									System.out.println("Weapon not found");
								}
							}
							if (world.getActiveRoom().getEnemy().getHp() <= 0) {
								if (world.getX() == 4 && world.getY() == 0) {
									System.out.println("As you land the final blow, a bright light begins to consume everything around you");
									System.out.println("After you come to your senses, you realize that you are laying down in a field of grass");
									System.out.println("You Win!");
									System.exit(0);
								} else if (world.getActiveRoom().getBoss()) {
									System.out.println("You killed the Boss, and a legendary item appears");
								} else {
									world.getActiveRoom().addItem(new Item("Rotten Food", 4, 1, 2)); // second time item was created: 10 points
									System.out.println("You killed the monster, and it dropped some food");
								}
								world.getActiveRoom().killEnemy();
								fight = false;
								dialogue = doors(world) + dialogue(world);
							}
							break;
						case "use":
							for(Item item : player.getInventory()) {
								if (item.getType() == 2) {
									n++;
								}
							}
							if (n > 0) {
								System.out.println("Food:");
								for(Item item : player.getInventory()) {
									if (item.getType() == 2) {
										System.out.print(item.getName());
										System.out.println(" - HEAL: " + item.getHealth() + " - USES: " + item.getDurability());
									}
								}
								while (true) {
									System.out.print("> ");
									response = in.nextLine();
									if (player.getNames().indexOf(response) != -1 && player.getInventory().get(player.getNames().indexOf(response)).getType() == 2) {
										player.setHp(player.getHp() + player.getInventory().get(player.getNames().indexOf(response)).getHealth());
										player.getInventory().get(player.getNames().indexOf(response)).setDurability(player.getInventory().get(player.getNames().indexOf(response)).getDurability() - 1);
										if (player.getHp() > maxHealth) {
											player.setHp(maxHealth);
										}
										System.out.println("Used " + response);
										if (player.getInventory().get(player.getNames().indexOf(response)).getDurability() == 0) {;
											player.removeItem(player.getInventory().get(player.getNames().indexOf(response)));
										}
										break;
									} else {
										System.out.println("Food not found");
									}
								}
							} else {
								System.out.println("You have no food");
							}
							n = 0;
							break;
						default:
							System.out.println("Unknown command");
							break;
					}
					first = false;
				}
				first = true;
			}
			if (newRoom) {
				System.out.print(dialogue);
				newRoom = false;
			}
			System.out.print("> ");
			response = in.nextLine();
			switch (response.toLowerCase()) {
				case "help":
					System.out.println("Commands:\nNorth\nSouth\nEast\nWest\nInventory\nLook\nStatus\nMap\nTake\nUse");
					break;
				case "status":
					System.out.println("HP: " + player.getHp());
					break;
				case "map":
					System.out.println(world.toString(false));
					break;
				case "look":
					newRoom = true;
					break;
				case "north":
					if (world.getX() == 4 && world.getY() == 1 && !world.bossesConquered()) { // room before boss room
							System.out.println("The door won't budge");
					} else {
						if (world.north()) {
							newRoom = true;
							dialogue = doors(world) + dialogue(world);
						} else {
							System.out.println("There is nothing in that direction");
						}
					}
					break;
				case "east":
					if (world.east()) {
						newRoom = true;
						dialogue = doors(world) + dialogue(world);
					} else {
						System.out.println("There is nothing in that direction");
					}
					break;
				case "south":
					if (world.south()) {
						newRoom = true;
						dialogue = doors(world) + dialogue(world);
					} else {
						System.out.println("There is nothing in that direction");
					}
					break;
				case "west":
					if (world.west()) {
						newRoom = true;
						dialogue = doors(world) + dialogue(world);
					} else {
						System.out.println("There is nothing in that direction");
					}
					break;
				case "inventory":
					if (!player.getInventory().isEmpty()) {
						for(Item item : player.getInventory()) {
							if (item.getType() == 1) {
								System.out.print(item.getName());
								System.out.print(" - ATK: " + item.getHealth() + " - DUR: " + item.getDurability());
								System.out.println();
							} else if (item.getType() == 2) {
								System.out.print(item.getName());
								System.out.print(" - HEAL: " + item.getHealth() + " - USES: " + item.getDurability());
								System.out.println();
							}
						}
					} else {
						System.out.println("There is nothing in your inventory");
					}
					break;
				case "take": 
					if (!world.getActiveRoom().getItems().isEmpty()) {
						System.out.println("You took the " + world.getActiveRoom().getItems().get(0).getName());
						if (world.getActiveRoom().getItems().get(0).getType() == 4) {
							System.out.println("It added " + world.getActiveRoom().getItems().get(0).getHealth() + " to your maximum health");
							maxHealth += world.getActiveRoom().getItems().get(0).getHealth();
							player.setHp(maxHealth);
						} else {
							player.addItem(world.getActiveRoom().getItems().get(0));
						}
						
						world.getActiveRoom().clrItems();
						dialogue = doors(world) + dialogue(world);
					} else {
						System.out.println("There is nothing to take");
					}
					break;
				case "use":
					for(Item item : player.getInventory()) {
						if (item.getType() == 2) {
							n++;
						}
					}
					if (n > 0) {
						System.out.println("Food:");
						for(Item item : player.getInventory()) {
							if (item.getType() == 2) {
								System.out.print(item.getName());
								System.out.println(" - HEAL: " + item.getHealth() + " - USES: " + item.getDurability());
							}
						}
						while (true) {
							System.out.print("> ");
							response = in.nextLine();
							if (player.getNames().indexOf(response) != -1 && player.getInventory().get(player.getNames().indexOf(response)).getType() == 2) {
								player.setHp(player.getHp() + player.getInventory().get(player.getNames().indexOf(response)).getHealth());
								player.getInventory().get(player.getNames().indexOf(response)).setDurability(player.getInventory().get(player.getNames().indexOf(response)).getDurability() - 1);
								if (player.getHp() > maxHealth) {
									player.setHp(maxHealth);
								}
								System.out.println("Used " + response);
								if (player.getInventory().get(player.getNames().indexOf(response)).getDurability() == 0) {;
									player.removeItem(player.getInventory().get(player.getNames().indexOf(response)));
								}
								break;
							} else {
								System.out.println("Food not found");
							}
						}
					} else {
						System.out.println("You have no food");
					}
					n = 0;
					break;
				default:
					System.out.println("Unknown command");
					break;
			}
		}
	}
	public static String dialogue(World world) {
		String result = "";
		if (world.getX() == 4 && world.getY() == 1) {
			result += "Writing above the northern door says \"You may open this door once you have defeated all 4 bosses\"\n";
		}
		if (!world.getActiveRoom().getItems().isEmpty()) {
			if (world.getActiveRoom().getItems().get(0).getType() == 2) {
				result += "Food lies on the ground\n";
			} else if (world.getActiveRoom().getItems().get(0).getType() == 1) {
				result += "A weapon lies on the ground\n";
			} else {
				result += "An upgrade lies on the ground\n";
			}
		} else {
			result += "Nothing here\n";
		}
		return result;
	}
	public static String doors(World world) {
		int n = 0;
		String result = "";
		for (int i = 0; i < 4; i++) {
			if (world.getAdjacentRooms()[i] == 1) {
				n++;
			}
		}
		if (n > 1) {
			result += "There are doors to the ";
			if (world.getAdjacentRooms()[0] == 1) {
				result += "north ";
			}
			if (world.getAdjacentRooms()[1] == 1) {
				result += "east ";
			}
			if (world.getAdjacentRooms()[2] == 1) {
				result += "south ";
			}
			if (world.getAdjacentRooms()[3] == 1) {
				result += "west";
			}

		} else {
			result += "There is a door to the ";
			if (world.getAdjacentRooms()[0] == 1) {
				result += "north";
			}
			else if (world.getAdjacentRooms()[1] == 1) {
				result += "east";
			}
			else if (world.getAdjacentRooms()[2] == 1) {
				result += "south";
			}
			else if (world.getAdjacentRooms()[3] == 1) {
				result += "west";
			}
		}
		return result + "\n";
	}
	// 2 methods: 8 points
}
// Total: 18 points