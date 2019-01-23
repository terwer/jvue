module.exports = function(results) {
  var summary = results.reduce(
    function(seq, current) {
      current.messages.forEach(function(msg) {
        var logMessage = {
          filePath: current.filePath,
          ruleId: msg.ruleId,
          message: msg.message,
          line: msg.line,
          column: msg.column
        };

        if (msg.severity === 1) {
          logMessage.type = "warning";
          seq.warnings.push(logMessage);
        }
        if (msg.severity === 2) {
          logMessage.type = "error";
          seq.errors.push(logMessage);
        }
      });
      return seq;
    },
    {
      errors: [],
      warnings: []
    }
  );

  if (summary.errors.length > 0 || summary.warnings.length > 0) {
    var lines = summary.errors
      .concat(summary.warnings)
      .map(function(msg) {
        return (
          msg.type +
          " " +
          msg.ruleId +
          "  " +
          msg.filePath +
          ":" +
          msg.line +
          ":" +
          msg.column
        );
      })
      .join("\n");
    return lines + "\n";
  }
};
