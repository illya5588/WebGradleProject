package model;

import exceptions.MarkException;

public class Mark {
    protected int digitMark;
    protected char letterMark;
    protected WordMark wordMark;

    public Mark(int digitMark) throws MarkException {
        if (digitMark > 100 || digitMark < 0) {
            throw new MarkException(digitMark, " this mark is out of bound. It should to be between 0 and 100!");
        } else {
            this.digitMark = digitMark;
            this.letterMark = setLetterMark();
            this.wordMark = setWordMark();
        }
    }


    private char setLetterMark() {


        if (this.digitMark >= 90) {
            return 'A';
        }
        if (this.digitMark >= 80) {
            return 'B';
        }
        if (this.digitMark >= 70) {
            return 'C';
        }
        if (this.digitMark >= 60) {
            return 'D';
        }
        if (this.digitMark >= 50) {
            return 'F';
        }
        return 'U';

    }

    @Override
    public String toString() {
        return " digitMark=" + digitMark +
                ", letterMark=" + letterMark +
                ", wordMark='" + wordMark + '\'' +
                "}\n";
    }

    public int getDigitMark() {
        return digitMark;
    }

    public char getLetterMark() {
        return letterMark;
    }

    public WordMark getWordMark() {
        return wordMark;
    }
    //Enum
    private WordMark setWordMark() {

        if (this.digitMark >= 90) {
            return WordMark.EXCELLENT;
        }
        if (this.digitMark >= 70) {
            return WordMark.MERIT;
        }
        if (this.digitMark >= 50) {
            return WordMark.PASS;
        }
        return WordMark.UNCLASSIFIED;
    }
}
