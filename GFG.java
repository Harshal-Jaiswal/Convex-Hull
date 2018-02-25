package gfg;

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {

    public static long pointplace(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (((x2 - x1) * (y3 - y1)) - ((x3 - x1) * (y2 - y1)));
    }

    public static void main(String[] args) {
        //code

        Scanner sc = new Scanner(System.in);
        int points, i, j, k, x, y, min, max, on_line, co_linear, point_place;
        float mins, maxs, above, below;
        int minss, maxss, c, maxv, minv, ll;
        long p_p;
        System.out.println("Enter number of problems");
        int cases = sc.nextInt();
        String res = "";

        while (cases != 0) {
            x = 0;
            y = 1;
            min = 1001;
            max = 0;
            on_line = 2;
            co_linear = 0;
            point_place = 3;
            mins = (float) -99.0;
            maxs = (float) -99.0;
            maxv = 0;
            minv = 1001;
            above = 0;
            below = 0;

            System.out.println("Enter number of points");
            points = sc.nextInt();
            int[][] p = new int[points][5];
            int[] op = new int[points];
            for (i = 0; i < points; i++) {
                System.out.println("Enter x cordinate of point " + (i + 1) + " :");
                p[i][x] = sc.nextInt();
                System.out.println("Enter y cordinate of point " + (i + 1) + " :");
                p[i][y] = sc.nextInt();
                p[i][4] = 0;
                p[i][on_line] = 0;
                p[i][point_place] = 0;
                if (p[i][x] <= minv) {
                    min = i;
                    minv = p[i][x];
                }
                if (p[i][x] >= maxv) {
                    max = i;
                    maxv = p[i][x];
                }
            }
            int maxx, maxy;
            j = points - 1;
            ll = 0;
            while (true) {
                maxx = 0;
                maxy = 0;
                for (i = 0; i < points; i++) {
                    if (p[i][4] != 1) {
                        if (p[i][x] > maxx) {
                            maxx = p[i][x];
                        }
                    }
                }

                for (i = 0; i < points; i++) {
                    if (p[i][4] != 1) {
                        if (p[i][x] >= maxx && p[i][y] > maxy) {
                            maxy = p[i][y];
                            ll = i;
                        }
                    }
                }
                p[ll][4] = 1;
                op[j] = ll;
                if (j == 0) {
                    break;
                }
                j--;
            }

            if (points < 3) {
                res = res + "-1 \n";
            } else {

                p[min][on_line] = 1;
                p[max][on_line] = 0;
                p[min][point_place] = 0;
                p[max][point_place] = 0;
                minss = min;
                maxss = max;

                for (i = 0; i < points; i++) {
                    if (i != min && i != max) {
                        p_p = pointplace(p[min][x], p[min][y], p[max][x], p[max][y], p[i][x], p[i][y]);
                        if (p_p == 0) {
                            co_linear++;
                            p[i][point_place] = 0;
                        }
                        if (p_p < 0) {
                            below++;
                            p[i][point_place] = -1;
                        }
                        if (p_p > 0) {
                            above++;
                            p[i][point_place] = 1;
                        }
                    }
                }

                if (above == 0 && below == 0) {
                    res = res + "-1 \n";
                } else {
                    //res=res+"\n"+p[min][x]+" "+p[min][y];
                    j = min;
                    ll = 0;
                    if (below != 0) {
                        while (true) {
                            for (i = 0, c = 0; i < points; i++) {
                                if (p[i][point_place] < 0 && p[i][on_line] != 1) {
                                    ll = i;
                                    c++;
                                    for (k = 0; k < points; k++) {
                                        if (p[k][point_place] <= 0 && p[k][on_line] != 1 && i != j) {

                                            if ((p_p = pointplace(p[j][x], p[j][y], p[ll][x], p[ll][y], p[k][x], p[k][y])) < 0) {
                                                ll = k;
                                            }
                                        }

                                    }
                                }
                            }
                            if (c == 0 || ll == max) {
                                break;
                            }
                            p[ll][on_line] = 1;
                            //System.out.print("," + p[ll][x] + " " + p[ll][y]);
                            // res=res+","+p[ll][x]+" "+p[ll][y];
                            j = ll;

                        }
                    }

                    // System.out.print("," + p[max][x] + " " + p[max][y]);
                    p[max][on_line] = 0;
                    p[min][on_line] = 0;
                    ll = 0;
                    j = min;
                    if (above != 0) {
                        while (true) {
                            for (i = 0, c = 0; i < points; i++) {
                                if (p[i][point_place] > 0 && p[i][on_line] != 1) {
                                    ll = i;
                                    c++;
                                    for (k = 0; k < points; k++) {
                                        if (p[k][point_place] >= 0 && p[k][on_line] != 1 && i != j) {

                                            if ((p_p = pointplace(p[j][x], p[j][y], p[ll][x], p[ll][y], p[k][x], p[k][y])) > 0) {
                                                ll = k;
                                            }
                                        }

                                    }
                                }
                            }

                            if (ll == max || c == 0) {
                                break;
                            }
                            p[ll][on_line] = 1;
                            //System.out.print("," + p[ll][x] + " " + p[ll][y]);
                            //res=res+","+p[ll][x]+" "+p[ll][y];
                            j = ll;

                        }
                    }
                    //res=res+","+p[max][x]+" "+p[max][y];
                    p[max][on_line] = 1;
                    p[min][on_line] = 1;
                    for (k = 0; k < points; k++) {

                        if (p[op[k]][on_line] == 1 && k != 0) {
                            res = res + ", " + p[op[k]][x] + " " + p[op[k]][y];
                        } else if (p[op[k]][on_line] == 1 && k == 0) {
                            res = res + p[op[k]][x] + " " + p[op[k]][y];
                        }

                    }
                    if (cases != 1) {
                        res = res + "\n";
                    }
                }
            }
            cases--;
        }
        System.out.println("result: "+res);
    }
}

//1
//26
//803 930 59 23 168 70 457 394 43 12 374 230 920 422 538 785 325 199 371 316 527
//414 981 92 874 957 171 863 282 997 926 306 328 85 506 337 730 847 858 314 896 125 546 583 368 815 365 435 751 44 809 88 
//1
//14
//778 887 794 916 387 336 650 493 363 422 691 28 764 60 541 927 173 427 212 737 568 369 783 430 863 531 68 124 
