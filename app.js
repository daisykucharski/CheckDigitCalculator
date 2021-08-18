const calculator = (() => {
  const valueMapping = new Map([
    ["A", 1],
    ["B", 2],
    ["C", 3],
    ["D", 4],
    ["E", 5],
    ["F", 6],
    ["G", 7],
    ["H", 8],
    ["I", 9],
    ["J", 1],
    ["K", 2],
    ["L", 3],
    ["M", 4],
    ["N", 5],
    ["O", 6],
    ["P", 7],
    ["Q", 8],
    ["R", 9],
    ["S", 2],
    ["T", 3],
    ["U", 4],
    ["V", 5],
    ["W", 6],
    ["X", 7],
    ["Y", 8],
    ["Z", 9],
  ]);

  const calculateCheckDigit = (entryNumber) => {
    if (checkValidEntryNum(entryNumber)) {
      numericBaseValue = calculateNumericBaseValue(entryNumber.toUpperCase());
      checkDigitBaseValue =
        calculateAdjustedEvenSumValue(numericBaseValue) +
        calculateOddSumValue(numericBaseValue);
      checkDigit = (10 - (checkDigitBaseValue % 10)) % 10;
      return entryNumber.toUpperCase() + "-" + checkDigit;
    } else {
      return "Invalid entry number, try again";
    }
  };

  const checkValidEntryNum = (entryNumber) => {
    return entryNumber.length == 11 && entryNumber.charAt(3) == "-";
  };

  const calculateNumericBaseValue = (entryNumber) => {
    result = "";

    for (i = 0; i < 3; ++i) {
      result += getNumericValue(entryNumber.charAt(i));
    }

    result += entryNumber.substring(4);
    return result;
  };

  const getNumericValue = (char) => {
    char.toUpperCase();
    if (valueMapping.has(char)) {
      return valueMapping.get(char);
    } else {
      return char;
    }
  };

  const calculateAdjustedEvenSumValue = (numericBaseValue) => {
    result = 0;

    for (i = 1; i < 10; i += 2) {
      product = Number(numericBaseValue.charAt(i));
      product *= 2;
      if (product > 9) {
        ++product;
      }

      result += product % 10;
    }

    return result;
  };

  const calculateOddSumValue = (numericBaseValue) => {
    result = 0;

    for (i = 0; i < 10; i += 2) {
      add = Number(numericBaseValue.charAt(i));
      result += add;
    }
    
    return result;
  };

  return { calculateCheckDigit , calculateNumericBaseValue, calculateOddSumValue, calculateAdjustedEvenSumValue};
})();

function calculate() {
  const entryNumber = document.querySelector("#entryNum").value;
  const checkDigit = calculator.calculateCheckDigit(entryNumber);
  const result = document.querySelector("#checkDigit");
  result.innerText = checkDigit;
};

function copy() {
  const result = document.querySelector("#checkDigit").innerText;
  if (result !== "Invalid entry number, try again") {
    navigator.clipboard.writeText(result);
  }
}

document.querySelector("#calculateBtn").addEventListener("click", () => {
  calculate();
});

document.querySelector("#copyBtn").addEventListener("click", () => {
  copy();
});

document.addEventListener('keypress', function (event) {
  if (event.key === 'Enter') {
    calculate();
  }
  if (event.key === 'c') {
    copy();
  }
});

function test(entryNum) {
  const nbv = calculator.calculateNumericBaseValue(entryNum);
  return {
    odd: calculator.calculateOddSumValue(nbv),
    even: calculator.calculateAdjustedEvenSumValue(nbv)
  };

}