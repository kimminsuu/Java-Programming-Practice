package blackjack;

import java.util.*;

class Card {
	int theValue;
	int theSuit;
	public Card() {}
	public Card(int theValue, int theSuit) {
		this.theValue = theValue;
		this.theSuit = theSuit;
	}
}

class Deck {
	private Card[] deck;
	private int cardsUsed;
	Deck() {
		deck = new Card[52];
		for(int i = 0;i<52;i++) {
			deck[i] = new Card(i/4 + 1,i%4 + 1);
		}
	}
	public void shuffle(int seed) {
		Random random = new Random(seed);
		for(int i = deck.length-1; i>0;i--) {
			int rand = (int)(random.nextInt(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed = 0;
	}
	public Card dealCard() {
		if(cardsUsed == deck.length) {
			throw new IllegalStateException("No cards are left in the deck.");
		}
		cardsUsed++;
		return deck[cardsUsed-1];
	}
}

class Hand{
	Card[] now_hand = new Card[52];
	
} //Set of cards in your hand
class Computer extends Hand{
	int res = 0;
	int order = 0;
	int num_of_A = 0;
	String val; String suit;
	Card[] com = new Card[10];
	void result(int num) {
		order++;
		if(num == 1) {
			res += 11;
			num_of_A++;
		}
		else if(num == 11 || num == 12 || num == 13) {
			res += 10;
		}
		else {
			res += num;
		}
	}
	void print(int p_num) {
		System.out.print("Player"+ p_num+": ");
		for(int i = 0;i<order;i++) {
			if(com[i].theValue == 1) {val = "A";}
			else if(com[i].theValue == 11) {val = "J";}
			else if(com[i].theValue == 12) {val = "Q";}
			else if(com[i].theValue == 13) {val = "K";}
			else {val = Integer.toString(com[i].theValue);}
			
			if(com[i].theSuit == 1) {suit = "c";}
			else if(com[i].theSuit == 2) {suit = "h";}
			else if(com[i].theSuit == 3) {suit = "d";}
			else if(com[i].theSuit == 4) {suit = "s";}
			else {suit = Integer.toString(com[i].theSuit);}
			if(i == 0) {
				System.out.print(val+suit);
			}
			else {
				System.out.print(", "+val+suit);
			}
		}
		if(res >21) {
			if(num_of_A > 0) {
				res-=10;
				System.out.println(" ("+res+")");
				num_of_A--;
			}
			else {
				System.out.println(" ("+res+") - Bust!");
			}
		}
		else {System.out.println(" ("+res+")");}
	}
} //Player automatically participates
class Player extends Hand{
	int res = 0;
	int order = 0;
	int num_of_A = 0;
	String val; String suit;
	Card[] p1 = new Card[10];
	void result(int num) {
		order++;
		if(num == 1) {
			res += 11;
			num_of_A++;
		}
		else if(num == 11 || num == 12 || num == 13) {
			res += 10;
		}
		else {
			res += num;
		}
	}
	void print() {
		System.out.print("Player1: ");
		for(int i = 0;i<order;i++) {
			if(p1[i].theValue == 1) {val = "A";}
			else if(p1[i].theValue == 11) {val = "J";}
			else if(p1[i].theValue == 12) {val = "Q";}
			else if(p1[i].theValue == 13) {val = "K";}
			else {val = Integer.toString(p1[i].theValue);}
			
			if(p1[i].theSuit == 1) {suit = "c";}
			else if(p1[i].theSuit == 2) {suit = "h";}
			else if(p1[i].theSuit == 3) {suit = "d";}
			else if(p1[i].theSuit == 4) {suit = "s";}
			else {suit = Integer.toString(p1[i].theSuit);}
			if(i == 0) {
				System.out.print(val+suit);
			}
			else {
				System.out.print(", "+val+suit);
			}
		}
		if(res >21) {
			if(num_of_A > 0) {
				res-=10;
				System.out.println(" ("+res+")");
				num_of_A--;
			}
			else {
				System.out.println(" ("+res+") - Bust!");
			}
		}
		else {System.out.println(" ("+res+")");}
		
	}
} //Player you control
class House extends Hand{
	int res = 0;
	int order = 0;
	boolean trigger = false;
	int num_of_A = 0;
	String val;
	String suit;
	Card[] h = new Card[10];
	void result(int num) {
		order++;
		if(num == 1) {
			res += 11;
			num_of_A++;
		}
		else if(num == 11 || num == 12 || num == 13) {
			res += 10;
		}
		else {
			res += num;
		}
	}
	void print() {
		System.out.print("House: ");
		for(int i = 0;i<order;i++) {
			if(h[i].theValue == 1) {val = "A";}
			else if(h[i].theValue == 11) {val = "J";}
			else if(h[i].theValue == 12) {val = "Q";}
			else if(h[i].theValue == 13) {val = "K";}
			else {val = Integer.toString(h[i].theValue);}
			
			if(h[i].theSuit == 1) {suit = "c";}
			else if(h[i].theSuit == 2) {suit = "h";}
			else if(h[i].theSuit == 3) {suit = "d";}
			else if(h[i].theSuit == 4) {suit = "s";}
			else {suit = Integer.toString(h[i].theSuit);}
			if(i == 0 && trigger == false) {
				System.out.print("Hidden");
			}
			else if(i == 0 && trigger == true) {
				System.out.print(val+suit);
			}
			else {
				System.out.print(", "+val+suit);
			}
		}
		if(res >21) {
			if(num_of_A > 0) {
				res-=10;
				System.out.println(" ("+res+")");
				num_of_A--;
			}
			else {
				System.out.println(" ("+res+") - Bust!");
			}
		}
		else if(res <= 21 && trigger == false) {
			System.out.println();
			trigger = true;
		}
		else {System.out.println(" ("+res+")"); }
	}
}

public class blackjack {

	public static void main(String[] args) {
		int seed = Integer.parseInt(args[0]);
		int player = Integer.parseInt(args[1]);
		Deck deck = new Deck();
		deck.shuffle(seed);
		int next_hand = 0;
		
		Scanner scn = new Scanner(System.in);
		Hand hand = new Hand();
		Computer[] computer = new Computer[player-1];
		for(int i = 0;i<player-1;i++) {
			computer[i] = new Computer();
		}
		Player player1 = new Player();
		House house = new House();
		for(int i = 0;i<52;i++) {
			hand.now_hand[i] = deck.dealCard();
		}
		
		//처음 입력받기
		player1.p1[0] = hand.now_hand[0]; 
		player1.result(player1.p1[0].theValue);
		player1.p1[1] = hand.now_hand[player + 1]; 
		player1.result(player1.p1[1].theValue);
		for(int i = 0;i<computer.length;i++) {
			computer[i].com[0] = hand.now_hand[i+1];
			computer[i].result(computer[i].com[0].theValue);
			computer[i].com[1] = hand.now_hand[player + 2 + i];
			computer[i].result(computer[i].com[1].theValue);
		}
		house.h[0] = hand.now_hand[player];
		house.result(house.h[0].theValue);
		house.h[1] = hand.now_hand[player * 2 + 1];
		house.result(house.h[1].theValue);
		next_hand = (player+1)*2;
		
		//print first
		house.print();
		player1.print();
		for(int i = 0;i<computer.length;i++) {
			computer[i].print(i+2);
		}
		System.out.println();
		//player1's turn
		
		String hit_or_stand;
		boolean bool = true;
		System.out.println("--- Player1 turn ---");
		player1.print();
		while(bool) {
			hit_or_stand = scn.nextLine();
			switch(hit_or_stand) {
			case "Hit":
				player1.p1[player1.order] = hand.now_hand[next_hand++];
				player1.result(player1.p1[player1.order].theValue);
				player1.print();
				if(player1.res > 21) {
					bool = false;
				}
				break;
			case "Stand":
				player1.print();
				bool = false;
				break;
			default:
				System.out.println("wrong command");
				break;
			}
		}
		System.out.println();
		scn.close();
		
		//other players' turn
		Random random = new Random();
		for(int i = 0;i<computer.length;i++) {
			int j = i+2;
			System.out.println("--- Player"+ j + " turn ---");
			bool = true;
			computer[i].print(i+2);
			while(bool) {
				if(computer[i].res < 14) {
					System.out.println("Hit");
					computer[i].com[computer[i].order] = hand.now_hand[next_hand++];
					computer[i].result(computer[i].com[computer[i].order].theValue);
					computer[i].print(i+2);
					if(computer[i].res > 21) {
						bool = false;
						System.out.println();
						break;
					}
				}
				else if (computer[i].res > 17){
					System.out.println("Stand");
					computer[i].print(i+2);
					bool = false;
					System.out.println();
					break;
				}
				else {
					int is_hit = (int)(random.nextInt(2));
					if(is_hit == 1) {
						System.out.println("Hit");
						computer[i].com[computer[i].order] = hand.now_hand[next_hand++];
						computer[i].result(computer[i].com[computer[i].order].theValue);
						computer[i].print(i+2);
						if(computer[i].res > 21) {
							bool = false;
							System.out.println();
							break;
						}
					}
					else {
						System.out.println("Stand");
						computer[i].print(i+2);
						bool = false;
						System.out.println();
						break;
					}
				}
			}
		}
		//house's turn
		System.out.println("--- House turn ---");
		bool = true;
		house.print();
		while(bool) {
			if(house.res < 17) {
				System.out.println("Hit");
				house.h[house.order] = hand.now_hand[next_hand++];
				house.result(house.h[house.order].theValue);
				house.print();
				if(house.res > 21) {
					bool = false;
					System.out.println();
					break;
				}
			}
			else {
				System.out.println("Stand");
				house.print();
				bool = false;
				break;
			}
		}
		
		//game results
		System.out.println("--- Game Results ---");
		house.print();
		if(house.res >21) {
			if(player1.res <= 21) {
				System.out.print("[Win] ");
				player1.print();
			}
			else {
				System.out.print("[Lose] ");
				player1.print();
			}
			for(int i = 0;i<computer.length;i++) {
				if(computer[i].res <= 21) {
					System.out.print("[win] ");
					computer[i].print(i+2);
				}
				else {
					System.out.print("[lose] ");
					computer[i].print(i+2);
				}
			}
		}
		else if(house.res == 21) {
			if(player1.res <= 21) {
				if(player1.res <house.res) {
					System.out.print("[Lose] ");
					player1.print();
				}
				else {
					System.out.print("[Draw] ");
					player1.print();
				}
			}
			else {
				System.out.print("[Lose] ");
				player1.print();
			}
			for(int i = 0;i<computer.length;i++) {
				if(computer[i].res <= 21) {
					if(computer[i].res <21) {
						System.out.print("[lose] ");
						computer[i].print(i+2);
					}
					else {
						System.out.print("[Draw] ");
						computer[i].print(i+2);
					}
				}
				else {
					System.out.print("[lose] ");
					computer[i].print(i+2);
				}
			}
		}
		else {
			if(player1.res <= 21) {
				if(player1.res <house.res) {
					System.out.print("[Lose] ");
					player1.print();
				}
				else if(player1.res >house.res) {
					System.out.print("[Win] ");
					player1.print();
				}
				else {
					System.out.print("[Draw] ");
					player1.print();
				}
			}
			else {
				System.out.print("[Lose] ");
				player1.print();
			}
			for(int i = 0;i<computer.length;i++) {
				if(computer[i].res <= 21) {
					if(computer[i].res < house.res) {
						System.out.print("[lose] ");
						computer[i].print(i+2);
					}
					else if(computer[i].res > house.res) {
						System.out.print("[Win] ");
						computer[i].print(i+2);
					}
					else {
						System.out.print("[Draw] ");
						computer[i].print(i+2);
					}
				}
				else {
					System.out.print("[lose] ");
					computer[i].print(i+2);
				}
			}
		}
	}
}
