package mega.system;

import java.io.Serializable;
import java.util.List;

import javax.swing.JLabel;

import mega.jeux.chess.Chess;
import mega.jeux.tictactoe.TicTacToe;
import mega.system.Partie.Jeu;
import mega.utils.Utils;
import mega.vues.MainVue;

public class Statistique implements Serializable{
	
		private MainVue vuePrincipale;
		private Joueur joueur1;
		private MegaJeuModel model;
		
					public Statistique(MainVue vuePrincipale) { 
							this.vuePrincipale = vuePrincipale;
							this.joueur1 = vuePrincipale.getJoueur1();
							this.model = vuePrincipale.getModel();
					}
			
				/*
				 * 
				 * 
				 *  PARTIES SAUVEGARDEE 
				 *  PANEL 1
				 * 	
				 */

					public void updateLabelsSauvegardeTTT(JLabel lbl_partie_sauvegarder1,JLabel lbl_partie_sauvegarder2,JLabel lbl_partie_sauvegarder3) { 
						  joueur1.chargeProgressionJoueur();
						  List<TicTacToe> psTTT = joueur1.getPartiesSauvegarderTTT();
						  System.out.println("Partie Sauvegarder TTT: "+psTTT.toString());
						  if(vuePrincipale.getJeu_selectionner() == Jeu.TICTACTOE) { 
							  if(psTTT != null) { 
								   switch(psTTT.size()) { 
								   	
								   case 0:
									   lbl_partie_sauvegarder1.setText("Partie 1 Vide");
									   lbl_partie_sauvegarder2.setText("Partie 2 Vide");
									   lbl_partie_sauvegarder3.setText("Partie 3 Vide");
									   break;
								   case 1:
									   checkPartieDisponibleTTT(psTTT,lbl_partie_sauvegarder1,0);
									   lbl_partie_sauvegarder2.setText("Partie 2 Vide");
									   lbl_partie_sauvegarder3.setText("Partie 3 Vide");
									   break;
									   
								   case 2:
									   checkPartieDisponibleTTT(psTTT,lbl_partie_sauvegarder1,0);
									   checkPartieDisponibleTTT(psTTT,lbl_partie_sauvegarder2,1);
									   lbl_partie_sauvegarder3.setText("Partie 3 Vide");
									   break;
									   
								   default:
									   checkPartieDisponibleTTT(psTTT,lbl_partie_sauvegarder1,0);
									   checkPartieDisponibleTTT(psTTT,lbl_partie_sauvegarder2,1);
									   checkPartieDisponibleTTT(psTTT,lbl_partie_sauvegarder3,2);
									   break;
								   }
							  }
						  }
						  
					}
					
					
					public void updateLabelsSauvegardeChess(JLabel lbl_partie_sauvegarder1,JLabel lbl_partie_sauvegarder2,JLabel lbl_partie_sauvegarder3) { 
						 joueur1.chargeProgressionJoueur();
						 List<Chess> psChess = joueur1.getPartiesSauvegarderChess();
						 System.out.println("Partie Sauvegarder Chess: "+psChess.toString());
						 if(vuePrincipale.getJeu_selectionner() == Jeu.CHESS) { 
						 if(psChess != null) { 
						   switch(psChess.size()) { 
						   	
						   case 0:
							   lbl_partie_sauvegarder1.setText("Partie 1 Vide");
							   lbl_partie_sauvegarder2.setText("Partie 2 Vide");
							   lbl_partie_sauvegarder3.setText("Partie 3 Vide");
							   
							   break;
						   case 1:
							   checkPartieDisponibleChess(psChess,lbl_partie_sauvegarder1,0);
							   lbl_partie_sauvegarder2.setText("Partie 2 Vide");
							   lbl_partie_sauvegarder3.setText("Partie 3 Vide");
							   break;
							   
						   case 2:
							   checkPartieDisponibleChess(psChess,lbl_partie_sauvegarder1,0);
							   checkPartieDisponibleChess(psChess,lbl_partie_sauvegarder2,1);
							   lbl_partie_sauvegarder3.setText("Partie 3 Vide");
							   break;
							   
						   default:
							   checkPartieDisponibleChess(psChess,lbl_partie_sauvegarder1,0);
							   checkPartieDisponibleChess(psChess,lbl_partie_sauvegarder2,1);
							   checkPartieDisponibleChess(psChess,lbl_partie_sauvegarder3,2);
							   break;
						   }
					  }
						 }
					
					}
				private void checkPartieDisponibleTTT(List<TicTacToe> tictactoe, JLabel label,int index) { 
						if(index < tictactoe.size()) {
							TicTacToe partie = tictactoe.get(index);
							label.setText("Vs "+partie.getIntegrateur().getPseudoj2()+" "+Utils.dateToString("YYYY-MM-dd",partie.getIntegrateur().getDate()));
						}
						else {
								label.setText("Partie Vide "+index);
						}
				}

				private void checkPartieDisponibleChess(List<Chess> chess, JLabel label,int index) { 
					if(index < chess.size()) {
						Chess partie = chess.get(index);
							 label.setText("Vs "+partie.getIntegrateur().getPseudoj2()+" "+Utils.dateToString("YYYY-MM-dd",partie.getIntegrateur().getDate()));
					}
					else {
							label.setText("Partie Vide "+index);
					}
			}
				
					
/*______________________________________________________________________________________________________________________________*/
					
										
					
					
					
					/*
					 * 
					 * 
					 *  MEILLEURS JOUEURS 
					 *  PANEL 2
					 * 	
					 */
					public void afficheMeilleursJoueurs(JLabel lbl_j1,JLabel lbl_sc1,JLabel lbl_j2,JLabel lbl_sc2,JLabel lbl_j3,JLabel lbl_sc3,
							JLabel lbl_j4,JLabel lbl_sc4,JLabel lbl_j5,JLabel lbl_sc5) { 
						List<Joueur> joueurs = model.topCinqJoueurs();
						for(Joueur j : joueurs) { 
							j.chargeProgressionJoueur();
						}
						if(joueurs != null) {
						
						switch(joueurs.size()) { 
							case 0:
								
								
								return;
								
							case 1:
									
								lbl_j1.setText(joueurs.get(0).getPseudonyme());
								lbl_sc1.setText(joueurs.get(0).calculeScore()+"");
								break;
							case 2:
								lbl_j1.setText(joueurs.get(0).getPseudonyme());
								lbl_j2.setText(joueurs.get(1).getPseudonyme());
								lbl_sc1.setText(joueurs.get(0).calculeScore()+"");
								lbl_sc2.setText(joueurs.get(1).calculeScore()+"");
								
								break;
							case 3:
								lbl_j1.setText(joueurs.get(0).getPseudonyme());
								lbl_j2.setText(joueurs.get(1).getPseudonyme());
								lbl_j3.setText(joueurs.get(2).getPseudonyme());
								
								lbl_sc1.setText(joueurs.get(0).calculeScore()+"");
								lbl_sc2.setText(joueurs.get(1).calculeScore()+"");
								lbl_sc3.setText(joueurs.get(2).calculeScore()+"");
								break;
								
							case 4:
								lbl_j1.setText(joueurs.get(0).getPseudonyme());
								lbl_j2.setText(joueurs.get(1).getPseudonyme());
								lbl_j3.setText(joueurs.get(2).getPseudonyme());
								lbl_j4.setText(joueurs.get(3).getPseudonyme());
								
								lbl_sc1.setText(joueurs.get(0).getPseudonyme());
								lbl_sc2.setText(joueurs.get(1).getPseudonyme());
								lbl_sc3.setText(joueurs.get(2).getPseudonyme());
								lbl_sc4.setText(joueurs.get(3).getPseudonyme());
								break;

								default:
									lbl_j1.setText(joueurs.get(0).getPseudonyme());
									lbl_j2.setText(joueurs.get(1).getPseudonyme());
									lbl_j3.setText(joueurs.get(2).getPseudonyme());
									lbl_j4.setText(joueurs.get(3).getPseudonyme());
									lbl_j5.setText(joueurs.get(4).getPseudonyme());
									
									lbl_sc1.setText(joueurs.get(0).getPseudonyme());
									lbl_sc2.setText(joueurs.get(1).getPseudonyme());
									lbl_sc3.setText(joueurs.get(2).getPseudonyme());
									lbl_sc4.setText(joueurs.get(3).getPseudonyme());
									lbl_sc5.setText(joueurs.get(4).getPseudonyme());
									
									
								break;
							}
						}
					}
					
/*______________________________________________________________________________________________________________________________*/
					
					
					
					/*
					 * 
					 * 
					 *  TROIS DERNIERES PARTIES
					 *  PANEL 3
					 * 	
					 */
						
					
					public void afficheTroisDernierePartieJouee(Jeu jeu_selectionner,JLabel lbl_partie1,JLabel lbl_partie2,JLabel lbl_partie3) { 
						joueur1.chargeProgressionJoueur();
						Progression p = joueur1.getProgression();
						if(p != null) { 
							
						if(jeu_selectionner == Jeu.CHESS) {
						
							 List<Partie> troisDerniereParties = p.troisDernierePartiesChess();
									//System.out.println("Chess: "+troisDerniereParties.toString());
								switch(troisDerniereParties.size()) { 
									
								case 0:
									lbl_partie1.setText("Partie 1 Indisponible");
									lbl_partie2.setText("Partie 2 Indisponible");
									lbl_partie3.setText("Partie 3 Indisponible");
									return;
									
								case 1:
									lbl_partie1.setText(troisDerniereParties.get(0).statPartie());
									lbl_partie2.setText("Partie 2 Indisponible");
									lbl_partie3.setText("Partie 3 Indisponible");
									break;
								case 2: 
									
									lbl_partie1.setText(troisDerniereParties.get(0).statPartie());
									lbl_partie2.setText(troisDerniereParties.get(1).statPartie());
									lbl_partie3.setText("Partie 3 Indisponible");
									break;
									
								default:
									if(troisDerniereParties != null) {
										lbl_partie1.setText(troisDerniereParties.get(0).statPartie());
										lbl_partie2.setText(troisDerniereParties.get(1).statPartie());
										lbl_partie3.setText(troisDerniereParties.get(2).statPartie());
									}
									break;
								}
						}
						
						else 
							if(jeu_selectionner == Jeu.TICTACTOE){
								 List<Partie> troisDerniereParties = p.troisDernierePartiesTTT();
							//		System.out.println("Chess: "+troisDerniereParties.toString());
									switch(troisDerniereParties.size()) { 
										
									case 0:
										
										lbl_partie1.setText("Partie 1 Indisponible");
										lbl_partie2.setText("Partie 2 Indisponible");
										lbl_partie3.setText("Partie 3 Indisponible");
										return;
										
									case 1:
										
										lbl_partie1.setText(troisDerniereParties.get(0).statPartie());
										lbl_partie2.setText("Partie 2 Indisponible");
										lbl_partie3.setText("Partie 3 Indisponible");
										break;
									case 2: 

										lbl_partie1.setText(troisDerniereParties.get(0).statPartie());
										lbl_partie2.setText(troisDerniereParties.get(1).statPartie());
										lbl_partie3.setText("Partie 3 Indisponible");
										break;
										
									default:
										if(troisDerniereParties != null) {
											lbl_partie1.setText(troisDerniereParties.get(0).statPartie());
											lbl_partie2.setText(troisDerniereParties.get(1).statPartie());
											lbl_partie3.setText(troisDerniereParties.get(2).statPartie());
										}
										break;
									}
								}
						}
					}
					
					
					
					
					
/*______________________________________________________________________________________________________________________________*/
					
										
					
					
					
					/*
					 * 
					 * 
					 *  PROGRESSION SCORE TOTAL
					 *  PANEL4
					 * 	
					 */
					
					public void progressionTotal(JLabel lb_total_score,JLabel lb_parties_gagnees,JLabel lbl_partie_egalites,JLabel lbl_parties_perdues) { 
						 if(joueur1.getProgression() != null) { 
							 lb_total_score.setText("Votre Score: "+joueur1.getProgression().getScore());
							 lb_parties_gagnees.setText("Partie Gagn\u00E9es:"+joueur1.getProgression().getNbG());
							 lbl_partie_egalites.setText("Egalit\u00E9s:"+joueur1.getProgression().getNbE());
							 lbl_parties_perdues.setText("Perdues: "+joueur1.getProgression().getNbP());
						 }
					}
				
						
					
					
/*______________________________________________________________________________________________________________________________*/
					
										
}
